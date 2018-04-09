package mybatis.hackday.mapper;

import mybatis.hackday.dto.Likes;
import mybatis.hackday.model.LikesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikesMapper {

    Likes findByCategoryIdAndPostNoAndCommentIdAndUserId(@Param("categoryId") int categoryId, @Param("postNo") int postNo, @Param("commentId") int commentId, @Param("userId") int userId);
    List<Likes> findByCategoryIdAndPostNoAndCommentId(@Param("categoryId") int categoryId, @Param("postNo") int postNo, @Param("commentId") int commentId);
    void insert(LikesModel likesModel);
    void delete(int id);

}
