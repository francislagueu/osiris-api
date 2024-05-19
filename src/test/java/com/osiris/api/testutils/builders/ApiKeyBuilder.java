package com.osiris.api.testutils.builders;

import com.osiris.api.entities.ApiKey;
import com.osiris.api.entities.Company;

import static com.osiris.api.BaseIntegrationTest.random;

/**
 * Created By francislagueu on 5/17/24
 */
public class ApiKeyBuilder {

    public static ApiKey apiKey(final Company company) {
        return ApiKey.builder().name(random()).companyId(company.getId()).build();
    }
}
