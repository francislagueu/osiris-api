package com.osiris.api.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created By francislagueu on 5/13/24
 */
@ResponseStatus(code = FORBIDDEN)
public class NotAllowedException extends RootException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NotAllowedException() {
        super(FORBIDDEN);
    }
}
