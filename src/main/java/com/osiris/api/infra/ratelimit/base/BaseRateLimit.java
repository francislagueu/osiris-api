package com.osiris.api.infra.ratelimit.base;

import io.github.bucket4j.Bandwidth;

/**
 * Created By francislagueu on 5/15/24
 */
public abstract class BaseRateLimit {
    public abstract String getName();

    public abstract Bandwidth getLimit();
}
