package mybatis.hackday.repository;

import mybatis.hackday.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RedisPostRepository {

    Map<String, Post> findAllPost();
    void savePost(List<Post> post);

}
