package com.easy.server.service;

import com.easy.common.core.enums.MailCodeType;
import com.easy.common.core.exception.CustomizeException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

/**
 * 邮件服务
 * </p>
 *
 * @author Matt
 */
@Service
@Slf4j
public class MailService {


    @Value(value = "${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 发送简单邮件
     *
     * @param to      接收者
     * @param subject 主题
     * @param context 内容
     */
    public void sendSampleMail(String to, MailCodeType subject, String context) {
        sendSampleMail(to, subject.getSubject(), context);
    }

    /**
     * 发送简单邮件
     *
     * @param to      接收者
     * @param subject 主题
     * @param context 内容
     */
    public void sendSampleMail(String to, String subject, String context) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(context);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送简单邮件失败:", e);
            throw new CustomizeException("发送邮件失败");
        }

    }

    /**
     * 发送附件邮件
     *
     * @param to             接收者
     * @param subject        主题
     * @param context        内容
     * @param attachmentName 附件名
     * @param filePath       文件路径
     */
    public void sendAttachmentMail(String to, String subject, String context, String attachmentName, String filePath) {
        try {
            // 创建一个复杂的消息邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context);
            // 上传文件
            helper.addAttachment(attachmentName, new File(filePath));
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("发送附件邮件失败:", e);
            throw new CustomizeException("发送邮件失败");
        }

    }

    /**
     * 发送模板邮件
     *
     * @param to           接收者
     * @param subject      主题
     * @param templatePath 模板路径
     * @param arguments    参数
     */
    public void sendTemplateMail(String to, String subject, String templatePath, String... arguments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(this.buildTemplateContext(templatePath, arguments), true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("发送模板邮件失败:", e);
            throw new CustomizeException("发送邮件失败");
        }

    }

    private String buildTemplateContext(String templatePath, String... arguments) {
        // 加载邮件html模板
        Resource resource = new ClassPathResource(templatePath);
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuilder buffer = new StringBuilder();
        String line = "";
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.error("读取模板失败:", e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 替换html模板中的参数
        return MessageFormat.format(buffer.toString(), arguments);
    }
}