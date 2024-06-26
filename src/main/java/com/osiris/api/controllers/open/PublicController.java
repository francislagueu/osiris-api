package com.osiris.api.controllers.open;

import com.osiris.api.constants.AppUrls;
import com.osiris.api.rabbitmq.publishers.EventPublisher;
import com.osiris.api.services.WebhookSiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Created By francislagueu on 5/17/24
 */
@Slf4j
@RestController
@RequestMapping(PublicController.BASE_URL)
@RequiredArgsConstructor
public class PublicController {
    public static final String BASE_URL = AppUrls.PUBLIC;

    private final EventPublisher eventPublisher;
    private final WebhookSiteService webhookSiteService;

    @Value("${rabbitmq.publishers.webhook.exchange}")
    private String exchange;

    @Value("${rabbitmq.publishers.webhook.routingkey}")
    private String routingKey;

    @GetMapping("/hello-world")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping("/publish")
    @ResponseStatus(HttpStatus.OK)
    public String publish() {
        this.eventPublisher.publish(this.exchange, this.routingKey, Map.of("test", "test"));
        return "published";
    }

    @GetMapping("/call-external-api")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> callExternalAPI() {
        return this.webhookSiteService.post(Map.of());
    }

    @GetMapping("/call-external-api-with-cb")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> callExternalAPIWithCircuitBreaker() {
        return this.webhookSiteService.postWithCircuitBreaker(Map.of());
    }
}
