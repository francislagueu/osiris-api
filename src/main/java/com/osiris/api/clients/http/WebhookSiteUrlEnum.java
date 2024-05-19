package com.osiris.api.clients.http;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created By francislagueu on 5/13/24
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WebhookSiteUrlEnum {
    POST("/");
    private final String url;
}
