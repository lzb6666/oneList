package com.whu.onelist.vcode;

import com.whu.onelist.mapper.UserMapper;
import com.whu.onelist.mapper.VCodeMapper;
import com.whu.onelist.user.UserService;
import com.whu.onelist.util.MailService;
import com.whu.onelist.util.SnowFlake;
import com.whu.onelist.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * author: create by zhong
 * description: com.whu.onelist.vcode
 * date:2019/4/8
 */
@Service
public class VCodeService {
    private static final Logger log = LoggerFactory.getLogger(VCodeService.class);

    //setter注入
    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    @Autowired
    public void setvCodeMapper(VCodeMapper vCodeMapper) {
        this.vCodeMapper = vCodeMapper;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    //setter注入结束


    private MailService mailService;
    private VCodeMapper vCodeMapper;
    private UserService userService;

    private String template="【OneList】你正在${type}，你的验证码为${code}。";

    void sendVCode(String email,String phoneNum,Integer type) throws MessagingException {
        String code;
        long expirationDate=System.currentTimeMillis()+300;
        String registry;
        if (phoneNum==null||phoneNum.equals("")){
            code=sendMailMsg(email,type);
            registry=email;
        }else {
            code=sendPhoneMsg(phoneNum,type);
            registry=phoneNum;
        }
        if (vCodeMapper.insert(registry,code,type,expirationDate)!=1){
            log.error("插入验证码出错"+registry+code+type+expirationDate);
            throw new RuntimeException("未知错误");
        }

    }

    boolean vlfForgetCode(String email, String phoneNum,String vcode){
        String registry;
        if (phoneNum==null||phoneNum.equals("")){
            registry=email;
        }else {
            registry=phoneNum;
        }
        boolean result=vCodeMapper.select(registry,vcode,2,System.currentTimeMillis())==1;
        if (result){
            vCodeMapper.delete(registry,2);
        }
        return result;
    }

    User vlfRegistryCode(String email, String phoneNum, String vcode){
        String registry;
        if (phoneNum==null||phoneNum.equals("")){
            registry=email;
        }else {
            registry=phoneNum;
        }
        if (vCodeMapper.select(registry,vcode,1,System.currentTimeMillis())==1){
            User user=userService.createUser(phoneNum,email);
            if (user!=null){
                vCodeMapper.delete(registry,1);
            }
            return user;
        }
        return null;
    }

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

    private String sendMailMsg(String mail,int type) throws MessagingException {
        String code=genVCode();
        mailService.sendMail(mail,"OneList",getVCodeMsg(code,type));
        return code;
    }

    private String sendPhoneMsg(String phoneNum,int type){

        return genVCode();
    }
}
