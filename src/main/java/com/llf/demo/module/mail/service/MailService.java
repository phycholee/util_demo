package com.llf.demo.module.mail.service;

import com.llf.demo.module.mail.model.Mail;

/**
 * @author: Oliver.li
 * @Description: 邮件发送接口
 * @date: 2018/6/15 10:18
 */
public interface MailService {

    /**
     * @author: Oliver.li
     * @Description: 发送文本邮件
     * @date: 2018/6/15 10:21
     */
    void sendSimpleMail(Mail mail);

    /**
     * @author: Oliver.li
     * @Description: 发送图片
     * @date: 2018/6/15 11:08
     */
    void sendInlineMail(Mail mail);

    /**
     * @author: Oliver.li
     * @Description: 发送模板邮件
     * @date: 2018/6/15 16:18
     */
    void sendTemplateMail(Mail mail);
}
