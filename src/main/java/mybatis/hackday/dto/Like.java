package mybatis.hackday.dto;

import lombok.Data;

@Data
public class Like {

    private int id;
    private int categoryId;
    private int postId;
    private int commentId;
    private int userId;

}
