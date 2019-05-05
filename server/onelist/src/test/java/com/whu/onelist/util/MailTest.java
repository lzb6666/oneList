package com.whu.onelist.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private MailService mailService;
    @Test
    public void mailTest(){
        try {
            mailService.sendMail("q1339675740@163.com","test","测试用");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
