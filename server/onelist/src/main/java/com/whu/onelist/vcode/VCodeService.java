package com.whu.onelist.vcode;

import com.whu.onelist.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * author: create by zhong
 * description: com.whu.onelist.vcode
 * date:2019/4/8
 */
@Service
public class VCodeService {

    @Autowired
    private MailService mailService;

    private String template="【OneList】你正在${type}，你的验证码为${code}。";

    private String getVCodeMsg(String code,int type){
        String msg;
        switch (type){
            case 1:
                msg=template.replace("${type}","注册");
                break;
            case 2:
                msg=template.replace("${type}","重置密码");
                break;
            default:
                throw new RuntimeException("非法的验证码请求");
        }
        return msg.replace("${code}",code);
    }

    private String genVCode(){
        return  String.valueOf(ThreadLocalRandom.current().nextInt(1000,9999));
    }

    public String sendMailMsg(String mail,int type) throws MessagingException {
        String code=genVCode();

        mailService.sendMail(mail,"OneList",getVCodeMsg(code,type));
        return code;
    }

    public String sendPhoneMsg(String phoneNum,int type){

        return genVCode();
    }
}
