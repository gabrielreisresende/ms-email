package com.resendegabriel.emailms.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resendegabriel.emailms.dto.EmailData;
import com.resendegabriel.emailms.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${ms.email.queue.content}")
    public void readMessage(@Payload String rabbitInput) {
        log.debug("Reading rabbit data. data{}", rabbitInput);

        try {
            EmailData emailData = mapper.readValue(rabbitInput, EmailData.class);
            emailService.sendEmail(emailData);
        } catch (Exception e) {
            log.error("Erro na leitura da fila - input{}", rabbitInput);
            throw new RuntimeException("Erro na leitura da fila");
        }
    }
}
