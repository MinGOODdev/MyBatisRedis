package mybatis.redis.hackday.controller;

import mybatis.redis.hackday.dto.Category;
import mybatis.redis.hackday.dto.Post;
import mybatis.redis.hackday.mapper.CategoryMapper;
import mybatis.redis.hackday.mapper.PostMapper;
import mybatis.redis.hackday.model.DefaultResponse;
import mybatis.redis.hackday.model.PostModel;
import mybatis.redis.hackday.model.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("board")
public class PostController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("all")
    public ResponseEntity<DefaultResponse> list() {
        DefaultResponse res = new DefaultResponse();
        List<Post> posts = postMapper.findAll();

        res.setData(posts);
        res.setMsg("post 전체 목록");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<DefaultResponse> listByCategory(@PathVariable int categoryId) {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryMapper.findOne(categoryId);
        List<Post> posts = postMapper.findByCategoryId(category.getId());

        res.setData(posts);
        res.setMsg("해당 카테고리 post");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{categoryId}/{no}")
    public ResponseEntity<DefaultResponse> postByCategory(@PathVariable int categoryId, @PathVariable int no) {
        DefaultResponse res = new DefaultResponse();
        Post post = postMapper.findByCategoryIdAndNo(categoryId, no);
        postMapper.updateHit(post);

        res.setData(post);
        res.setMsg("해당 카테고리 게시글 내용");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
