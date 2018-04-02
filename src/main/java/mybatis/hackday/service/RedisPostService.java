package mybatis.hackday.service;

import mybatis.hackday.dto.Post;
import mybatis.hackday.repository.RedisPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class RedisPostService implements RedisPostRepository {

    private static final String KEY = "Post";
    private RedisTemplate<String, Post> redisTemplate;
    private HashOperations<String, String, Post> hashOperations;

    @Autowired
    public RedisPostService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, Post> findAllPost() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void savePost(List<Post> post) {
        for(Post p : post)
            if(p.getHit() >= 10)
                hashOperations.put(KEY, p.getTitle(), p);
    }

}
