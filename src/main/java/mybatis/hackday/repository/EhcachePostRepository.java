package mybatis.hackday.repository;

import mybatis.hackday.dto.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EhcachePostRepository {

    List<Post> findAllNoCache();
    List<Post> findAllCache();
    void refresh();

}
