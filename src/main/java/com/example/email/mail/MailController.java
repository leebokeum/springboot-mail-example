package com.example.email.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
public class MailController {

    private MailSenderTemplate mailSenderTemplate;

    @Autowired
    public void setMailSender(MailSenderTemplate mailSender) {
        this.mailSenderTemplate = mailSender;
    }

    private MailSenderDefault mailSenderDefault;

    @Autowired
    public void setMailSenderDefault(MailSenderDefault mailSenderDefault) {
        this.mailSenderDefault = mailSenderDefault;
    }

    @GetMapping("/send-mail-template")
    String sendMail() throws IOException, MessagingException {
        mailSenderTemplate.sendMail("leebokeum@hanmail.net", "leebokeum.com입니다.", "안녕하세요. 반갑습니다. leebokeum.com입니다.");
        return "성공";
    }

    @GetMapping("/send-mail-default")
    String sendMailDefault() throws IOException, MessagingException {
        mailSenderDefault.sendMail("leebokeum@hanmail.net", "leebokeum.com입니다.", "안녕하세요. 반갑습니다. leebokeum.com입니다.");
        return "성공";
    }
}
