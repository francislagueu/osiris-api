package com.osiris.api.rabbitmq.listeners;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static com.osiris.api.rabbitmq.listeners.EventListener.RABBIT_ASYNC_EVENT_LISTENER_ID;

/**
 * Created By francislagueu on 5/13/24
 */
@Component
public class RabbitMQApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    RabbitListenerEndpointRegistry registry;

    /**
     * This event is executed as late as conceivably possible to indicate that the application is
     * ready to service requests.
     */
    @Override
    public void onApplicationEvent(final @NonNull ApplicationReadyEvent event) {
        this.registry.getListenerContainer(RABBIT_ASYNC_EVENT_LISTENER_ID).start();
    }
}
