package mybatis.hackday.mapper;

import mybatis.hackday.dto.Post;
import mybatis.hackday.model.PostModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // resultMap (commentId DESC)
    Post findAllByCategoryIdAndPostNoWithCommentsOrderByCommentIdDesc(@Param("categoryId") int categoryId, @Param("postNo") int postNo);

    // resultMap (likesCount DESC)
    Post findAllByCategoryIdAndPostNoWithCommentsOrderByLikesCountDesc(@Param("categoryId") int categoryId, @Param("postNo") int postNo);

    List<Post> findAll();
    List<Post> findByCategoryIdOrderByNoDesc(int categoryId);

    // MyBatis에서 객체를 이용하지 않고 파라미터는 여러개 넘기는 경우 @Param가 필요하다.
    Post findByCategoryIdAndNo(@Param("categoryId") int categoryId, @Param("no") int no);

    void updateHit(Post post);
    void insert(PostModel postModel);
    void deleteByCategoryIdAndNo(@Param("categoryId") int categoryId, @Param("no") int no);

    Post findTopByCategoryIdOrderByNoDesc(int categoryId);

}
