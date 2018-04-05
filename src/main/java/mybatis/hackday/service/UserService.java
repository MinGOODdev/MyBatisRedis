package mybatis.hackday.service;

import mybatis.hackday.dto.User;
import mybatis.hackday.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String userId, String password) {
        User user = userMapper.findByUserId(userId);
        if(user == null) return null;
        if(user.getPassword().equals(password) == false) return null;
        return user;
    }

}
