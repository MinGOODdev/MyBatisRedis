package mybatis.hackday.controller;

import mybatis.hackday.dto.User;
import mybatis.hackday.model.DefaultResponse;
import mybatis.hackday.model.StatusEnum;
import mybatis.hackday.service.MyAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @PostMapping("guest/login")
    public ResponseEntity<DefaultResponse> login(@RequestBody User user) {
        DefaultResponse res = new DefaultResponse();

        res.setData(myAuthenticationProvider.authenticate(user.getUserId(), user.getPassword()));
        res.setMsg("Login Successful");
        res.setStatusEnum(StatusEnum.SUCCESS);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
