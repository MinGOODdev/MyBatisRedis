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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    // Get All Post (전체 게시글 목록) *
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

    // Create Post (need to login) (게시글 등록) *
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

    // Get all Post by Category (카테고리별 게시글 리스트) *
    @GetMapping("board/{categoryId}")
    public ResponseEntity<DefaultResponse> listByCategory(@PathVariable int categoryId) {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryService.findById(categoryId);
        List<Post> posts = postService.findByCategoryIdOrderByNoDesc(category.getId());

        res.setData(posts);
        res.setMsg("해당 카테고리의 게시글 리스트");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("{categoryId}/{no}")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResponseEntity<DefaultResponse> deletePostWithCommentsAndLikes(@PathVariable int categoryId, @PathVariable int no) throws IllegalAccessException {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryService.findById(categoryId);
        Post post = postService.findByCategoryIdAndNo(category.getId(), no);
        postService.deleteByCategoryIdAndNo(category.getId(), post.getNo());

        res.setData(post);
        res.setMsg("해당 게시글 삭제");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}