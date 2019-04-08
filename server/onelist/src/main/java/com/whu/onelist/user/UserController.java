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
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity register(String password, HttpSession session){
        String userID=(String)session.getAttribute("userID");
        User user=userService.Register(userID,password);
        return new ResponseEntity(user,HttpStatus.OK);
    }


}
