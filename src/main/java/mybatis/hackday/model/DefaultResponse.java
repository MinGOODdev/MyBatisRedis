package mybatis.hackday.model;

import lombok.Data;

@Data
public class DefaultResponse {

    private StatusEnum statusEnum;
    private Object data;
    private String msg;

    public DefaultResponse() {
        this.statusEnum = StatusEnum.FAIL;
        this.data = data;
        this.msg = msg;
    }
}
