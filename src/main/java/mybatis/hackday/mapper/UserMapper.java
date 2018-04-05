package mybatis.hackday.mapper;

import mybatis.hackday.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByUserId(String userId);

}
