package mybatis.hackday.mapper;

import mybatis.hackday.dto.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    Category findOne(int categoryId);

}
