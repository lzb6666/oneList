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

    @GetMapping("")
    public ResponseEntity login(String phoneNum,String email, String password, HttpSession session){
        User user=userService.findUser(email,phoneNum,password);
        if (user==null){
            ResultMsg msg=new ResultMsg(ResultMsg.Type.FAIL,"用户名或密码错误");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else{
            session.setAttribute("userID",user.getUserID());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }



    @PostMapping("")
    public ResponseEntity register(String password, HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        Boolean register=(Boolean) session.getAttribute("registry");
        if (register==null||!register){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"非法请求"),HttpStatus.BAD_REQUEST);
        }
        if (userService.updatePwd(userID,password)){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"注册成功"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"注册失败"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userID}")
    public ResponseEntity resetPwd(String newPassword,HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        Boolean forget=(Boolean) session.getAttribute("forget");
        if (forget==null||!forget){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"非法请求"),HttpStatus.BAD_REQUEST);
        }
        if (userService.updatePwd(userID,newPassword)){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"修改密码成功"),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"修改密码失败"),HttpStatus.BAD_REQUEST);
        }
    }


}
