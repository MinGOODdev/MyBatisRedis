package mybatis.hackday.service;

import mybatis.hackday.dto.Comment;
import mybatis.hackday.dto.Likes;
import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.LikesMapper;
import mybatis.hackday.model.LikesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class LikesService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    /**
     * 사용자의 좋아요 요청을 받아
     * 좋아요가 있으면 삽입 없으면 삭제하여 좋아요 취소를 합니다.
     * @param categoryId
     * @param postNo
     * @param commentId
     */
    public void likeOrNoLike(int categoryId, int postNo, int commentId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        Comment comment = commentService.findByCategoryIdAndPostNoAndId(categoryId, postNo, commentId);

        // 좋아요 & 좋아요 취소 판단
        if(findByCategoryIdAndPostNoAndCommentIdAndUserId(categoryId, postNo, commentId, user.getId()) != null) {
            Likes likes = findByCategoryIdAndPostNoAndCommentIdAndUserId(categoryId, postNo, commentId, user.getId());
            commentService.subLikesCount(comment);
            delete(likes.getId());
        }
        else {
            commentService.addLikesCount(comment);
            insert(categoryId, postNo, commentId);
        }

    }

    public Likes findByCategoryIdAndPostNoAndCommentIdAndUserId(int categoryId, int postNo, int commentId, int userId) {
        return likesMapper.findByCategoryIdAndPostNoAndCommentIdAndUserId(categoryId, postNo, commentId, userId);
    }

    public List<Likes> findByCategoryIdAndPostNoAndCommentId(int categoryId, int postNo, int commentId) {
        return likesMapper.findByCategoryIdAndPostNoAndCommentId(categoryId, postNo, commentId);
    }

    public void insert(int categoryId, int postNo, int commentId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        LikesModel likesModel = new LikesModel();
        likesModel.setCategoryId(categoryId);
        likesModel.setPostNo(postNo);
        likesModel.setCommentId(commentId);
        likesModel.setUserId(user.getId());
        likesMapper.insert(likesModel);
    }

    public void delete(int likesId) {
        likesMapper.delete(likesId);
    }

}
