package mybatis.hackday.controller;

import mybatis.hackday.dto.Category;
import mybatis.hackday.dto.Post;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.PostModel;
import mybatis.hackday.model.StatusEnum;
import mybatis.hackday.service.CategoryService;
import mybatis.hackday.service.PostService;
import mybatis.hackday.service.RedisPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostService postService;
    @Autowired
    private RedisPostService redisPostService;
    @Autowired
    private CategoryService categoryService;

    // Get All Post
    @GetMapping("board/all")
    public ResponseEntity<DefaultResponse> list() {
        DefaultResponse res = new DefaultResponse();
        List<Post> posts = postService.findAll();
        redisPostService.savePost(posts);

        res.setData(posts);
        res.setMsg("전체 게시글 목록");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Create Post (need to login)
    @PostMapping("{categoryId}/post")
    public ResponseEntity<DefaultResponse> createPost(@PathVariable int categoryId, @RequestBody PostModel postModel) {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryService.findById(categoryId);
        postService.insert(category.getId(), postModel);

        res.setData(postModel);
        res.setMsg("게시글 등록");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // Get all Post by Category
    @GetMapping("board/{categoryId}")
    public ResponseEntity<DefaultResponse> listByCategory(@PathVariable int categoryId) {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryService.findById(categoryId);
        List<Post> posts = postService.findByCategoryId(category.getId());

        res.setData(posts);
        res.setMsg("해당 카테고리의 게시글 리스트");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}