package mybatis.hackday.model;

import lombok.Data;

@Data
public class LikesModel {

    private int id;
    private int categoryId;
    private int postNo;
    private int commentId;
    private int userId;

}
