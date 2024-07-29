package com.resendegabriel.emailms.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static com.resendegabriel.emailms.util.TestUtilities.getEmailData;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    JavaMailSender mailSender;

    @Test
    void shouldSendEmailSuccessfully() {
        emailService.sendEmail(getEmailData());

        then(mailSender).should().send(any(SimpleMailMessage.class));
    }

    @Test
    void shouldThrowExceptionWhenSendEmailWithEmptyData() {
        doThrow(new MailSendException("Error sending email")).when(mailSender).send(any(SimpleMailMessage.class));

        assertThrows(MailSendException.class, () -> emailService.sendEmail(getEmailData()));
    }

}