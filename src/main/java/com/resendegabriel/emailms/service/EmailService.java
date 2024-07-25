package com.resendegabriel.emailms.service;

import com.resendegabriel.emailms.dto.EmailData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String emailSender;

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(EmailData data) {
        log.info("Sending email - START - {}", data);

        try {
            var message = new SimpleMailMessage();
            message.setFrom(emailSender);
            message.setTo(data.to());
            message.setSubject(data.subject());
            message.setText(data.body());

            mailSender.send(message);
        } catch (MailSendException e) {
            throw new MailSendException("Error sending email");
        }

        log.info("Sending email - END - {}", data);
    }
}
