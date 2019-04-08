package com.whu.onelist.vcode;

import com.whu.onelist.util.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@RequestMapping("/vcode")
@RestController
public class VCodeController {

    @Autowired
    private VCodeService vCodeService;
    @GetMapping("/{type}")
    public ResponseEntity getVCode(@PathVariable Integer type, String email, String phoneNum) throws MessagingException {
        if (phoneNum==null||phoneNum.equals("")){
            vCodeService.sendMailMsg(email,type);
        }else {
            vCodeService.sendPhoneMsg(phoneNum,type);
        }
        return new ResponseEntity(new ResultMsg(1,"发送成功，请注意查收"), HttpStatus.OK);
    }
}
