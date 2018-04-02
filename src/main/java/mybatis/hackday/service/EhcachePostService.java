package mybatis.hackday.service;

import mybatis.hackday.dto.Post;
import mybatis.hackday.mapper.PostMapper;
import mybatis.hackday.repository.EhcachePostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EhcachePostService implements EhcachePostRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<Post> findAllNoCache() {
        slowQuery(2000);
        return postMapper.findAll();
    }

    // @Cacheable(value="", key"#method's argument")의 형태로도 사용할 수 있다.
    @Override
    @Cacheable(value="ehcache")
    public List<Post> findAllCache() {
        slowQuery(2000);
        return postMapper.findAll();
    }

    // @CacheEvict(value="", key"#method's argument")의 형태로도 사용할 수 있다.
    @Override
    @CacheEvict(value="ehcache")
    public void refresh() {
        logger.info("EHCache Clear!!");
    }

    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
