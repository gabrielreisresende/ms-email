package com.resendegabriel.emailms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resendegabriel.emailms.dto.EmailData;

public class TestUtilities {

    public static final ObjectMapper mapper = new ObjectMapper();

    private static String RABBIT_INPUT;

    private static EmailData emailData;


    public static String getValidRabbitInput() throws JsonProcessingException {
        return mapper.writeValueAsString(getEmailData());
    }

    public static String getInvalidRabbitInput() throws JsonProcessingException {
        return "invalid input";
    }

    public static EmailData getEmailData() {
        return EmailData.builder()
                .to("email@email.com")
                .subject("subject")
                .body("body")
                .build();
    }
}
