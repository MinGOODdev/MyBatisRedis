package mybatis.hackday.service;

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

@Service
public class LikesService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private UserService userService;

    public void likeOrNoLike(int categoryId, int postNo, int commentId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        // dislikes
        if(findByCategoryIdAndPostNoAndCommentIdAndUserId(categoryId, postNo, commentId, user.getId()) != null) {
            Likes likes = findByCategoryIdAndPostNoAndCommentIdAndUserId(categoryId, postNo, commentId, user.getId());
            delete(likes.getId());
        }
        else {
            insert(categoryId, postNo, commentId);
        }

    }

    public Likes findByCategoryIdAndPostNoAndCommentIdAndUserId(int categoryId, int postNo, int commentId, int userId) {
        return likesMapper.findByCategoryIdAndPostNoAndCommentIdAndUserId(categoryId, postNo, commentId, userId);
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
