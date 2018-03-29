package mybatis.redis.hackday.dto;

import lombok.Data;

@Data
public class Post {

    private int id;
    private int categoryId;
    private int no;
    private String title;
    private String body;
    private int hit;

}
