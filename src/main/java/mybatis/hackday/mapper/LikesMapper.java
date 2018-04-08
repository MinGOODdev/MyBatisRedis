package mybatis.hackday.mapper;

import mybatis.hackday.dto.Likes;
import mybatis.hackday.model.LikesModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikesMapper {

    Likes findByCategoryIdAndPostNoAndCommentIdAndUserId(@Param("categoryId") int categoryId, @Param("postNo") int postNo, @Param("commentId") int commentId, @Param("userId") int userId);
    void insert(LikesModel likesModel);
    void delete(int id);

}
