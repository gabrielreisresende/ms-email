package com.resendegabriel.emailms.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.Instant;

@Slf4j
@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardError> IOExceptionHandler(IOException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Erro na leitura dos dados ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListenerExecutionFailedException.class)
    public ResponseEntity<StandardError> listenerExecutionFailedExceptionHandler(ListenerExecutionFailedException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Erro na leitura da fila ", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<StandardError> mailSendExceptionHandler(MailSendException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Erro ao enviar email ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> runTimeExceptionHandler(RuntimeException ex, HttpServletRequest request) {
        return genericExceptionHandler(ex, request, "Erro inesperado ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<StandardError> genericExceptionHandler(Exception ex, HttpServletRequest request, String error, HttpStatus status) {
        var standardError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(error)
                .message(ex.getMessage())
                .path(request.getRequestURI());
        log.error("STATUS {} - path:{} - message:{}", status.value(), request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(status).body(standardError.build());
    }
}
