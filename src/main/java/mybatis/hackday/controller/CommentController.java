package mybatis.hackday.controller;

import mybatis.hackday.dto.Post;
import mybatis.hackday.model.CommentModel;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.StatusEnum;
import mybatis.hackday.service.CommentService;
import mybatis.hackday.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("board")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @PostMapping("{categoryId}/{postNo}/comment")
    public ResponseEntity<DefaultResponse> createComment(@PathVariable int categoryId, @PathVariable int postNo, @RequestBody CommentModel commentModel) {
        DefaultResponse res = new DefaultResponse();
        Post post = postService.findByCategoryIdAndNo(categoryId, postNo);
        commentService.insert(categoryId, post.getNo(), commentModel);

        res.setData(commentModel);
        res.setMsg("댓글 등록 성공");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
