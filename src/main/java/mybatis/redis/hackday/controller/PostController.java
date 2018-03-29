package mybatis.redis.hackday.controller;

import mybatis.redis.hackday.dto.Category;
import mybatis.redis.hackday.dto.Post;
import mybatis.redis.hackday.mapper.CategoryMapper;
import mybatis.redis.hackday.mapper.PostMapper;
import mybatis.redis.hackday.model.DefaultResponse;
import mybatis.redis.hackday.model.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("post")
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

    @GetMapping("{categoryId}")
    public ResponseEntity<DefaultResponse> listByCategory(@PathVariable int categoryId) {
        DefaultResponse res = new DefaultResponse();
        Category category = categoryMapper.findOne(categoryId);
        List<Post> posts = postMapper.findByCategoryId(category.getId());
        res.setData(posts);
        res.setMsg("해당 카테고리 post 내용");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DefaultResponse> detailPost(@PathVariable int id) {
        DefaultResponse res = new DefaultResponse();
        Post post = postMapper.findOne(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
