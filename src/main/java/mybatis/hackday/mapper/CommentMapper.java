package mybatis.hackday.mapper;

import mybatis.hackday.dto.Comment;
import mybatis.hackday.model.CommentModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> findByCategoryIdAndPostNo(@Param("categoryId") int categoryId, @Param("postNo") int postNo);
    Comment findByCategoryIdAndPostNoAndId(@Param("categoryId") int categoryId, @Param("postNo") int postNo, @Param("id") int id);
    void insert(CommentModel commentModel);

}
