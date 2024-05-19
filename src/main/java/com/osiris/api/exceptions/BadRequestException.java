package com.osiris.api.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created By francislagueu on 5/13/24
 */
@ResponseStatus(code = BAD_REQUEST)
public class BadRequestException extends RootException{
    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(final String message) {
        super(BAD_REQUEST, message);
    }
}
