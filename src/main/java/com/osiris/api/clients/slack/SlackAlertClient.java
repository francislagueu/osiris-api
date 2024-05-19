package com.osiris.api.clients.slack;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created By francislagueu on 5/13/24
 */
@Slf4j
@Getter
@Component
public class SlackAlertClient extends BaseSlackClient{
    private final String url;
    private final String channel;
    private final String env;

    public SlackAlertClient(
            @Value("${slack.env}") final String env,
            @Value("${slack.channels.api-alert.url}") final String url,
            @Value("${slack.channels.api-alert.name}") final String channel) {
        this.env = env;
        this.url = url;
        this.channel = channel;
    }
}
