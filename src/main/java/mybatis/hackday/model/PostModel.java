package mybatis.hackday.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostModel implements Serializable {

    private int id;
    private int categoryId;
    private int userId;
    private int no;
    private String title;
    private String body;

}
