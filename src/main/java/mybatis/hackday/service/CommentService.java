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

    public List<Comment> findByCategoryIdAndPostNo(int categoryId, int postNo) {
        return commentMapper.findByCategoryIdAndPostNo(categoryId, postNo);
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

}
