package com.resendegabriel.emailms.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resendegabriel.emailms.dto.EmailData;
import com.resendegabriel.emailms.service.EmailService;
import com.resendegabriel.emailms.util.TestUtilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.resendegabriel.emailms.util.TestUtilities.getEmailData;
import static com.resendegabriel.emailms.util.TestUtilities.getInvalidRabbitInput;
import static com.resendegabriel.emailms.util.TestUtilities.getValidRabbitInput;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailConsumerTest {

    @InjectMocks
    private EmailConsumer emailConsumer;

    @Mock
    private EmailService emailService;

    @Spy
    private ObjectMapper mapper = TestUtilities.mapper;

    @Test
    void shouldConsumeRabbitEmailQueueWhenValidInput() throws JsonProcessingException {
        when(mapper.readValue(getValidRabbitInput(), EmailData.class)).thenReturn(getEmailData());

        emailConsumer.readMessage(getValidRabbitInput());

        then(emailService).should().sendEmail(any(EmailData.class));
    }

    @Test
    void shouldThrowExceptionConsumingRabbitEmailQueueWhenIsAnInvalidInput() throws JsonProcessingException {
        assertThrows(Exception.class, () -> emailConsumer.readMessage(getInvalidRabbitInput()));
    }

}