package com.llf.demo.module.mail.service.impl;

import com.llf.demo.common.MailConstant;
import com.llf.demo.module.mail.model.Mail;
import com.llf.demo.module.mail.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

/**
 * @author: Oliver.li
 * @Description: 邮件发送服务实现
 * @date: 2018/6/15 10:22
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        message.setCc(mail.getCc());

        try {
            mailSender.send(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendInlineMail(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText("<html><body><img src=\"cid:image\" ></body></html>", true);

            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\96210\\Desktop\\image\\redmi6.jpg"));
            // addInline函数中资源名称image需要与正文中cid:image对应起来
            helper.addInline("image", file);
            mailSender.send(message);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void sendTemplateMail(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            //读取 html 模板
            Configuration cfg = getConfiguration();
            Template template = cfg.getTemplate(mail.getTemplateName() + ".ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getTemplateModel());
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static Configuration getConfiguration() throws IOException {
        Configuration cfg = new Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(MailConstant.TEMPLATE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }
}
