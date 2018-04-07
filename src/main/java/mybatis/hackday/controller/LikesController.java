package mybatis.hackday.controller;

import mybatis.hackday.dto.Category;
import mybatis.hackday.dto.Comment;
import mybatis.hackday.dto.Post;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.StatusEnum;
import mybatis.hackday.service.CategoryService;
import mybatis.hackday.service.CommentService;
import mybatis.hackday.service.LikesService;
import mybatis.hackday.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
public class LikesController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommentService commentService;
    @Autowired
    private LikesService likesService;
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    // 공감
    @GetMapping("{categoryId}/{postNo}/{commentId}")
    public ResponseEntity<DefaultResponse> likeComment(@PathVariable int categoryId, @PathVariable int postNo, @PathVariable int commentId) {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryService.findById(categoryId);
        Post post = postService.findByCategoryIdAndNo(category.getId(), postNo);
        Comment comment = commentService.findByCategoryIdAndPostNoAndId(category.getId(), post.getNo(), commentId);

        likesService.likeOrNoLike(category.getId(), post.getNo(), comment.getId());

        res.setData(comment);
        res.setMsg("좋아요");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
