package com.llf.demo.module.mail.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: Oliver.li
 * @Description: 邮件
 * @date: 2018/6/15 10:15
 */
public class Mail implements Serializable {

    /**
     * 发给多个人
     */
    private String[] to;

    /**
     * 抄送
     */
    private String[] cc;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容   简单文本 和附件邮件必填  其余的不需要
     */
    private String text;


    /**
     * 模板需要的数据   发送模板邮件必填
     */
    private Map<String,String> templateModel;

    /**
     * 选用哪个模板 发送模板邮件必填
     */
    private String templateName;

    public String[] getTo() {
        return to;
    }

    public void setTo(String... to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String... cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, String> getTemplateModel() {
        return templateModel;
    }

    public void setTemplateModel(Map<String, String> templateModel) {
        this.templateModel = templateModel;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", templateModel=" + templateModel +
                ", templateName='" + templateName + '\'' +
                '}';
    }
}
