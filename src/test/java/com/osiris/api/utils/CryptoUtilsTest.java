package com.osiris.api.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By francislagueu on 5/17/24
 */
public class CryptoUtilsTest {
    @Test
    void verifyRandomKey() {
        final int length = 10;
        Assertions.assertEquals(CryptoUtils.randomKey(length).length(), length * 2);
    }
}
