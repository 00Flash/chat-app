package com.zigix.chatapp.registration.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmail(String to, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            messageHelper.setFrom("chatapp@test.com");
            messageHelper.setTo(to);
            messageHelper.setSubject("Confirm your email");
            messageHelper.setText(content, true);

            mailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new IllegalStateException("Filed to send email");
        }
    }
}
