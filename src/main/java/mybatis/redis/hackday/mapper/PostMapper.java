package mybatis.redis.hackday.mapper;

import mybatis.redis.hackday.dto.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    Post findOne(int postId);
    List<Post> findAll();
    List<Post> findByCategoryId(int categoryId);
    Post findByNo(int no);
    void updateHit(Post post);

}
