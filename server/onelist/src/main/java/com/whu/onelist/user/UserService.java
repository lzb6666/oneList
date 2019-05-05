package com.whu.onelist.user;

import com.whu.onelist.mapper.UserMapper;
import com.whu.onelist.util.SnowFlake;
import com.whu.onelist.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private static final Logger log=LoggerFactory.getLogger(UserService.class);

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private UserMapper userMapper;

    User findUser(String email,String phoneNum,String password){
        if (phoneNum==null||phoneNum.equals("")){
            return userMapper.loginByEmail(email,password);
        }else {
            return userMapper.loginByPhone(phoneNum,password);
        }
    }

    boolean updatePwd(long userID, String password){
        return userMapper.updatePwd(userID,password)==1;
    }

    public User createUser(String phoneNum,String email){
        User user=new User(SnowFlake.nextId(),phoneNum,email);
        if (userMapper.insert(user)==1){
            return user;
        }else {
            log.error("创建用户出错："+user.getUserID()+user.getEmail()+user.getPhoneNum());
            throw new RuntimeException("未知错误");
        }
    }
}
