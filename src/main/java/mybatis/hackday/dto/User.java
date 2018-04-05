package mybatis.hackday.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private int id;
    private String userId;
    private String password;

}
