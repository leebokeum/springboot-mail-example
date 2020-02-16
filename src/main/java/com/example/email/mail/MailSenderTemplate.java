package com.example.email.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
//@PropertySource("classpath:mail.properties")
public class MailSenderTemplate {

    private JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setTemplateEngine(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void sendMail(String sendTo, String title, String content) throws MessagingException, IOException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //메일 제목 설정
        helper.setSubject(title);
        //수신자 설정
        helper.setTo(sendTo);
        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariable("content", content);
        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("mail-template",context);
        helper.setText(html, true);

        helper.addAttachment("static/leebokeum.png", new ClassPathResource("static/leebokeum.png"));

        //메일 보내기
        javaMailSender.send(message);
    }
}
