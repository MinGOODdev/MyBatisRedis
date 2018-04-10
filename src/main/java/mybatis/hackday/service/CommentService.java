package mybatis.hackday.service;

import mybatis.hackday.dto.Comment;
import mybatis.hackday.dto.Likes;
import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.CommentMapper;
import mybatis.hackday.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private LikesService likesService;

//    // 좋아요 (내림차순 정렬)
//    public List<Comment> findByCategoryIdAndPostNoOrderByLikesCountDesc(int categoryId, int postNo) {
//        return commentMapper.findByCategoryIdAndPostNoOrderByLikesCountDesc(categoryId, postNo);
//    }

    // 게시글에 댓글이 있는지 없는지 판단하기 위함
    public List<Comment> findByCategoryIdAndPostNo(int categoryId, int postNo) {
        return commentMapper.findByCategoryIdAndPostNo(categoryId, postNo);
    }

    public Comment findByCategoryIdAndPostNoAndId(int categoryId, int postNo, int commentId) {
        return commentMapper.findByCategoryIdAndPostNoAndId(categoryId, postNo, commentId);
    }

    public void insert(int categoryId, int postNo, CommentModel commentModel) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        commentModel.setCategoryId(categoryId);
        commentModel.setUserId(user.getId());
        commentModel.setPostNo(postNo);
        commentMapper.insert(commentModel);
    }

    public void addLikesCount(Comment comment) {
        commentMapper.addLikesCount(comment);
    }

    public void subLikesCount(Comment comment) {
        commentMapper.subLikesCount(comment);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteByCategoryIdAndPostNoAndId(int categoryId, int postNo, int commentId) throws IllegalAccessException {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        if(user.getId() == findByCategoryIdAndPostNoAndId(categoryId, postNo, commentId).getUserId()) {
            List<Likes> likes = likesService.findByCategoryIdAndPostNoAndCommentId(categoryId, postNo, commentId);
            for(Likes like : likes)
                likesService.delete(like.getId());
            commentMapper.deleteByCategoryIdAndPostNoAndId(categoryId, postNo, commentId);
        }
        else {
            throw new IllegalAccessException("댓글을 작성한 사용자가 아닙니다.");
        }
    }

    public void delete(int id) {
        commentMapper.delete(id);
    }

}
