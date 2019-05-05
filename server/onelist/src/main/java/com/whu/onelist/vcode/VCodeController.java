package com.whu.onelist.vcode;

import com.whu.onelist.util.ResultMsg;
import com.whu.onelist.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RequestMapping("/vcode")
@RestController
public class VCodeController {

    @Autowired
    public void setvCodeService(VCodeService vCodeService) {
        this.vCodeService = vCodeService;
    }

    private VCodeService vCodeService;


    @GetMapping("/{type}")
    public ResponseEntity getVCode(@PathVariable Integer type, String email, String phoneNum) throws MessagingException {
        vCodeService.sendVCode(email, phoneNum, type);
        return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"发送成功，请注意查收"), HttpStatus.OK);
    }

    @PostMapping("/"+VCodeType.REGISTRY)
    public ResponseEntity vlfRegistryCode(String email, String phoneNum,String vcode,HttpSession session){
        User user=vCodeService.vlfRegistryCode(email, phoneNum, vcode);
        if (user==null){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"验证码错误"), HttpStatus.NOT_FOUND);
        }else {
            session.setAttribute("registry",true);
            session.setAttribute("userID",user.getUserID());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }

    @PostMapping("/"+VCodeType.FORGET)
    public ResponseEntity vlfForgetCode(String email, String phoneNum,String vcode,HttpSession session){
        if (vCodeService.vlfForgetCode(email, phoneNum, vcode)){
            session.setAttribute("forget",true);
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"验证通过"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"验证码错误"), HttpStatus.NOT_FOUND);
        }
    }


}
