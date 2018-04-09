package mybatis.hackday.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Post implements Serializable {

    private static final long serialVersionUID = -5881130662885257748L;

    private int id;
    private int categoryId;
    private String categoryName;
    private int userId;
    private int no;
    private String title;
    private String body;
    private int hit;

    // resultMap
    private List<Comment> comments;

}
