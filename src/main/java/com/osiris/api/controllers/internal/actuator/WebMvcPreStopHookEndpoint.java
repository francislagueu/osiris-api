package com.osiris.api.controllers.internal.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.OK;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@Component
@ControllerEndpoint(id = "preStopHook")
public class WebMvcPreStopHookEndpoint {
    @ResponseStatus(OK)
    @GetMapping("/{delayInMillis}")
    public ResponseEntity<Void> preStopHook(@PathVariable("delayInMillis") final long delayInMillis)
            throws InterruptedException {
        log.info("[preStopHook] received signal to sleep for {}ms", delayInMillis);
        Thread.sleep(delayInMillis);
        return null;
    }
}
