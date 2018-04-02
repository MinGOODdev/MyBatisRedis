package mybatis.hackday.controller;

import mybatis.hackday.dto.Post;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.StatusEnum;
import mybatis.hackday.service.PostService;
import mybatis.hackday.service.RedisPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@SuppressWarnings("Duplicates")
@EnableCaching
@RequestMapping("ehcache")
public class EhcachePostController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostService postService;
    @Autowired
    private RedisPostService redisPostService;

    @GetMapping("all")
    public ResponseEntity<DefaultResponse> ehcacheList() {
        DefaultResponse res = new DefaultResponse();
        List<Post> posts = postService.findAll();
        redisPostService.savePost(posts);

        res.setData(posts);
        res.setMsg("post 전체 목록");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
