package com.whu.onelist.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
    // 主机名
    @Value("${mail.host}")
    private String host;

    // 连接协议
    @Value("${mail.protocol}")
    private String protocol;

    //发件人
    @Value("${mail.sender}")
    private String sender;

    //发件人对应的授权码
    @Value("${mail.key}")
    private String key;

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    public void sendMail(String dis,String subject,String content) throws MessagingException{
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail.smtp.host", host);
        properties.put("mail.debug", "true");
        Session session = Session.getInstance(properties);
        // 创建默认的 MimeMessage 对象
        MimeMessage message = new MimeMessage(session);

        // Set From: 头部头字段
        message.setFrom(new InternetAddress(sender));

        // Set To: 头部头字段
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress(dis));
        // Set Subject: 头部头字段
        message.setSubject(subject);
        // 设置消息体
        message.setText(content);

        Transport transport = session.getTransport();
        transport.connect(sender,key);
        transport.sendMessage(message,message.getAllRecipients());
        log.info("Sent message successfully...." + dis);
    }
}
