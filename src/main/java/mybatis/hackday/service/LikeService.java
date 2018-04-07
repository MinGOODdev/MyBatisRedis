package mybatis.hackday.service;

import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.LikeMapper;
import mybatis.hackday.model.LikeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class LikeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private UserService userService;

    public void insert(int categoryId, int postNo, int commentId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String userId = principal.getName();
        User user = userService.findByUserId(userId);

        LikeModel likeModel = new LikeModel();
        likeModel.setCategoryId(categoryId);
        logger.info("categoryId: {}", categoryId);
        likeModel.setPostNo(postNo);
        logger.info("postNo: {}", postNo);
        likeModel.setCommentId(commentId);
        logger.info("commentId: {}", commentId);
        likeModel.setUserId(user.getId());
        logger.info("userId: {}", user.getId());
        likeMapper.insert(likeModel);
    }

}
