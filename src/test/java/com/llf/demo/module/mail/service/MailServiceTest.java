package com.llf.demo.module.mail.service;

import com.llf.demo.module.mail.model.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/6/15 10:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail() {

        Mail mail = new Mail();
        mail.setTo("phycholee@qq.com");
        mail.setCc("llf95508@gmail.com");
        mail.setSubject("dinner");
        mail.setText("tonight 7 pm, I'll be in home, please come.");

        mailService.sendSimpleMail(mail);
    }

    @Test
    public void sendInlineMail() {

        Mail mail = new Mail();
        mail.setTo("phycholee@qq.com");
        mail.setCc("llf95508@gmail.com");
        mail.setSubject("girl");

        mailService.sendInlineMail(mail);
    }

    @Test
    public void sendTemplateMail(){
        Mail mail = new Mail();
        mail.setTo("phycholee@qq.com");
        mail.setSubject("hello");

        mail.setTemplateName("hello");
        Map<String, String> model = new HashMap<>();
        model.put("name", "王菲");
        mail.setTemplateModel(model);

        mailService.sendTemplateMail(mail);
    }
}