package com.osiris.api.testutils.builders;

import com.osiris.api.entities.Company;
import lombok.experimental.UtilityClass;

import static com.osiris.api.BaseIntegrationTest.random;

/**
 * Created By francislagueu on 5/17/24
 */
@UtilityClass
public class CompanyBuilder {

    public static Company company() {
        return Company.builder().name(random()).slug(random()).build();
    }

    public static Company platform() {
        return Company.builder().name(random()).slug(random()).isPlatform(true).build();
    }

    public static Company management() {
        return Company.builder().name(random()).slug(random()).isManagement(true).build();
    }

    public static Company internal() {
        return Company.builder().name(random()).slug(random()).isInternal(true).build();
    }
}
