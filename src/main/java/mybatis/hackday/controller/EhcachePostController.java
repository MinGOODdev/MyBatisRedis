package mybatis.hackday.controller;

import mybatis.hackday.dto.Post;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.StatusEnum;
import mybatis.hackday.service.EhcachePostService;
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
    private EhcachePostService ehcachePostService;

    @GetMapping("nocache")
    public ResponseEntity<DefaultResponse> noCacheList() {
        long start = System.currentTimeMillis();

        DefaultResponse res = new DefaultResponse();
        List<Post> posts = ehcachePostService.findAllNoCache();

        long end = System.currentTimeMillis();
        logger.info("noEHCache의 수행시간: {}", Long.toString(end - start));

        res.setData(posts);
        res.setMsg("post 전체 목록");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("cache")
    public ResponseEntity<DefaultResponse> cacheList() {
        long start = System.currentTimeMillis();

        DefaultResponse res = new DefaultResponse();
        List<Post> posts = ehcachePostService.findAllCache();

        long end = System.currentTimeMillis();
        logger.info("EHCache의 수행시간: {}", Long.toString(end - start));

        res.setData(posts);
        res.setMsg("post 전체 목록");
        res.setStatusEnum(StatusEnum.SUCCESS);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("refresh")
    public void refresh() {
        ehcachePostService.refresh();
    }

}
