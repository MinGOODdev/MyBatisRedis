package mybatis.hackday.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Post implements Serializable {

    private int id;
    private int categoryId;
    private int no;
    private String title;
    private String body;
    private int hit;

}
