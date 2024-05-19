package com.osiris.api.services;

import com.osiris.api.clients.http.WebhookSiteHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WebhookSiteService {
    private final WebhookSiteHttpClient client;

    public Mono<String> post(final Object request) {
        // Deserialization (if needed) is done at service level to keep code DRY.
        return this.client.post(request).map(response -> response);
    }

    public Mono<String> postWithCircuitBreaker(final Object request) {
        return this.client.postWithCircuitBreaker(request).map(response -> response);
    }
}