package com.osiris.api.rabbitmq.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static com.osiris.api.rabbitmq.configs.RabbitMQConfig.RABBIT_ASYNC_EVENT_LISTENER_FACTORY;

/**
 * Created By francislagueu on 5/13/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventListener {
    public static final String RABBIT_ASYNC_EVENT_LISTENER_ID = "EventListener";

    @Value("${rabbitmq.listeners.event.queue}")
    private String queueName;

    @RabbitListener(
            id = RABBIT_ASYNC_EVENT_LISTENER_ID,
            containerFactory = RABBIT_ASYNC_EVENT_LISTENER_FACTORY,
            queues = "${rabbitmq.listeners.event.queue}")
    public Mono<Void> process(final Message<?> message) {
        log.info("[RABBITMQ][SUB][{}] {}", this.queueName, message);
        return Mono.empty();
    }
}
