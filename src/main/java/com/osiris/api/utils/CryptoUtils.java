package com.osiris.api.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

/**
 * Created By francislagueu on 5/13/24
 */
@UtilityClass
public class CryptoUtils {
    public static String randomKey(final int length) {
        final byte[] apiKey = new byte[length];
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(apiKey);

        final StringBuilder sb = new StringBuilder();
        for (final byte b : apiKey) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
