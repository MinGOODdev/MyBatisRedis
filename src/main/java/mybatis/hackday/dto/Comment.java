package mybatis.hackday.dto;

import lombok.Data;

@Data
public class Comment {

    private int id;
    private int categoryId;
    private int postNo;
    private int userId;
    private String commentBody;
    private int likesCount;

}
