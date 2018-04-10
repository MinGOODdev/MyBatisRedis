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

    List<Comment> findByCategoryIdAndPostNoOrderByLikesCountDesc(@Param("categoryId") int categoryId, @Param("postNo") int postNo);

    void insert(CommentModel commentModel);
    void addLikesCount(Comment comment);
    void subLikesCount(Comment comment);
    void delete(int id);        // post를 지울 때 사용하기 위해서 만듬
    void deleteByCategoryIdAndPostNoAndId(@Param("categoryId") int categoryId, @Param("postNo") int postNo, @Param("id") int id);

}
