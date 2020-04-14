package com.example.rabbit.rabbitdemo.service.impl;

import com.example.rabbit.rabbitdemo.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtils {
    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendMail(MailDto mailDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("subject");
        message.setFrom("yingfeiqidaokuaile@qq.com");
        message.setTo("1229162706@qq.com");
        message.setText(mailDto.getContent());
        try {
            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }
}
