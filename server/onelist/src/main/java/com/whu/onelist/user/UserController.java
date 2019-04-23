package com.whu.onelist.user;

import com.whu.onelist.util.ResultMsg;
import com.whu.onelist.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{userID}")
    public ResponseEntity login(@PathVariable String userID, String password){
        User user=userService.findUser(userID,password);
        if (user==null){
            ResultMsg msg=new ResultMsg(2,"用户名或密码错误");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity register(String password, HttpSession session){
        String userID=(String)session.getAttribute("userID");
        Boolean register=(Boolean) session.getAttribute("registry");
        if (register==null||!register){
            return new ResponseEntity<>(new ResultMsg(2,"非法请求"),HttpStatus.BAD_REQUEST);
        }
        if (userService.updatePwd(userID,password)){
            return new ResponseEntity<>(new ResultMsg(1,"注册成功"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(2,"注册失败"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userID}")
    public ResponseEntity resetPwd(String newPassword,HttpSession session){
        String userID=(String)session.getAttribute("userID");
        Boolean forget=(Boolean) session.getAttribute("forget");
        if (forget==null||!forget){
            return new ResponseEntity<>(new ResultMsg(2,"非法请求"),HttpStatus.BAD_REQUEST);
        }
        if (userService.updatePwd(userID,newPassword)){
            return new ResponseEntity<>(new ResultMsg(1,"修改密码成功"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(2,"修改密码失败"),HttpStatus.BAD_REQUEST);
        }
    }


}
