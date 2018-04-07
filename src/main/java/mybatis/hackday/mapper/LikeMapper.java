package mybatis.hackday.mapper;

import mybatis.hackday.model.LikeModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {

    void insert(LikeModel likeModel);

}
