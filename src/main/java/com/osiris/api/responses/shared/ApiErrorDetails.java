package com.osiris.api.responses.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.io.Serializable;

/**
 * Created By francislagueu on 5/13/24
 */
@Builder
public record ApiErrorDetails(@JsonInclude(JsonInclude.Include.NON_NULL) String pointer, String reason)
        implements Serializable {}
