package cn.itcast.erp.biz.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;

/**
 * 发送邮件工具
 */
public class MailUtil {
    /**Spring邮件发送类*/
    private JavaMailSender sender;
    /**发件人*/
    private String from;

    /**
     * 发送邮件
     * @param to 接收人，多人时可用逗号分隔
     * @param subject 主题
     * @param text 邮件正文
     */
    public void sendMail(String to,String subject,String text) throws MessagingException {
        // 创建邮件
        javax.mail.internet.MimeMessage mimeMessage = sender.createMimeMessage();
        // 邮件包装工具
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        // 发件人
        helper.setFrom(from);
        // 收件人
        helper.setTo(to);
        // 邮件标题
        helper.setSubject(subject);
        // 邮件正文
        helper.setText(text);
        // 发送邮件
        sender.send(mimeMessage);
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
