package com.resendegabriel.emailms.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfig {

    @Value("${ms.email.queue.content}")
    private String queue;

    @Value("${ms.email.queue.content.dlq}")
    private String queueDLQ;

    @Value("${ms.email.exchange}")
    private String exchange;

    @Value("${ms.email.exchange.dlq}")
    private String exchangeDLQ;

    @Value("${ms.email.routing.key}")
    private String routingKey;

    @Value("${ms.email.routing.key.dlq}")
    private String routingKeyDLQ;

    @Bean
    public Queue queue() {
        log.info("Creating main queue: {}", queue);
        return QueueBuilder.durable(queue)
                .deadLetterExchange(exchangeDLQ)
                .deadLetterRoutingKey(routingKeyDLQ)
                .build();
    }

    @Bean
    public Exchange exchange() {
        log.info("Creating exchange: {}", exchange);
        return ExchangeBuilder.directExchange(exchange)
                .durable(true)
                .build();
    }

    @Bean
    public Binding binding() {
        log.info("Binding queue to exchange: {} -> {}", queue, exchange);
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey)
                .noargs();
    }

    @Bean
    public Queue queueDLQ() {
        log.info("Creating DLQ queue: {}", queueDLQ);
        return QueueBuilder.durable(queueDLQ).build();
    }

    @Bean
    public Exchange exchangeDLQ() {
        log.info("Creating DLQ exchange: {}", exchangeDLQ);
        return ExchangeBuilder.directExchange(exchangeDLQ)
                .durable(true)
                .build();
    }

    @Bean
    public Binding bindingDLQ() {
        log.info("Binding DLQ queue to DLQ exchange: {} -> {}", queueDLQ, exchangeDLQ);
        return BindingBuilder.bind(queueDLQ())
                .to(exchangeDLQ())
                .with(routingKeyDLQ)
                .noargs();
    }
}
