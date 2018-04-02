package mybatis.hackday.controller;

import mybatis.hackday.dto.Post;
import mybatis.hackday.service.RedisPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisPostController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisPostService redisPostService;

    @GetMapping("all")
    public Map<String, Post> findAllPost() {
        return redisPostService.findAllPost();
    }

}
