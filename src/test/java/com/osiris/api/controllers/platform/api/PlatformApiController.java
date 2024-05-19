package com.osiris.api.controllers.platform.api;

import com.osiris.api.constants.AppUrls;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By francislagueu on 5/17/24
 */
@Slf4j
@RestController
@RequestMapping(PlatformApiController.BASE_URL)
@RequiredArgsConstructor
public class PlatformApiController {
    public static final String BASE_URL = AppUrls.PLATFORM_API;

    @GetMapping("/hello-world")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        return "Hello world";
    }
}
