package org.example.film.commons.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.film.models.entities.Account;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendActivationEmail (Account account, String activationCode){
        String to = account.getEmail();
        String subject = "Kich hoat tai khoan cua ban";
        String content = "Vui long nhap vao lien ket sau de kich hoat tai khoan cua ban: http://localhost:8080/activate?code=" + activationCode;
        sendMail(to, subject, content);
    }

    public void sendMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
