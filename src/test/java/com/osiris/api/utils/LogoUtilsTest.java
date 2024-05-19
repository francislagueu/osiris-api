package com.osiris.api.utils;

import com.osiris.api.entities.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Created By francislagueu on 5/17/24
 */
public class LogoUtilsTest {
    @Test
    void verifyLogIdNullSafe() {
        Assertions.assertEquals(EMPTY, LogUtils.logId(null));
        Assertions.assertEquals(EMPTY, LogUtils.logId(new Company()));
    }

    @Test
    void verifyLogId() {
        final var company = new Company(1L);
        Assertions.assertEquals(LogUtils.logId(company), company.getId().toString());
    }

    @Test
    void verifyLogIdsNullSafe() {
        Assertions.assertEquals(LogUtils.logIds(null), List.of().toString());
        Assertions.assertEquals(LogUtils.logIds(List.of()), List.of().toString());
    }

    @Test
    void verifyLogIds() {
        final var company = new Company(1L);
        Assertions.assertEquals(LogUtils.logIds(List.of(company)), List.of(company.getId()).toString());
    }
}
