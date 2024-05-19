package com.osiris.api.rabbitmq.publishers;

import com.osiris.api.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static com.osiris.api.rabbitmq.configs.RabbitMQConfig.RABBIT_EVENT_PUBLISHER;
import static java.lang.String.format;

/**
 * Created By francislagueu on 5/13/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventPublisher {
    @Qualifier(RABBIT_EVENT_PUBLISHER)
    private final AmqpTemplate amqpTemplate;

    public void publish(
            @NonNull final String exchange,
            @NonNull final String routingKey,
            @NonNull final Object payload) {

        try {

            final String msg = JsonUtils.serializeToCamelCase(payload);

            final MessageProperties props =
                    MessagePropertiesBuilder.newInstance()
                            .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                            .setContentEncoding(StandardCharsets.UTF_8.toString())
                            .build();

            log.info(
                    "[RABBITMQ][PUB][{}] headers {} payload {} ", routingKey, props.getHeaders(), payload);

            final Message message = MessageBuilder.withBody(msg.getBytes()).andProperties(props).build();
            this.amqpTemplate.send(exchange, routingKey, message);

        } catch (final Exception ex) {
            log.error(
                    format(
                            "[RABBITMQ][PUB][%s] error publishing message with payload %s", routingKey, payload),
                    ex);
        }
    }
}
