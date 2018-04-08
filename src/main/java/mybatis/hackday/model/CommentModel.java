package mybatis.hackday.model;

import lombok.Data;

@Data
public class CommentModel {

    private int id;
    private int categoryId;
    private int userId;
    private int postNo;
    private String commentBody;
    private int likesCount;

}
