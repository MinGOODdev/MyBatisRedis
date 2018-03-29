package mybatis.redis.hackday.mapper;

import mybatis.redis.hackday.dto.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    Category findOne(int categoryId);

}
