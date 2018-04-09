package mybatis.hackday.service;

import mybatis.hackday.dto.Comment;
import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.CommentMapper;
import mybatis.hackday.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;

    // 좋아요 (내림차순 정렬)
    public List<Comment> findByCategoryIdAndPostNoOrderByLikesCountDesc(int categoryId, int postNo) {
        return commentMapper.findByCategoryIdAndPostNoOrderByLikesCountDesc(categoryId, postNo);
    }

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

}
