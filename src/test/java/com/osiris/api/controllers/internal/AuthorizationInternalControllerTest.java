package com.osiris.api.controllers.internal;

import com.osiris.api.BaseIntegrationTest;
import com.osiris.api.constants.AppHeaders;
import com.osiris.api.services.ApiKeyService;
import com.osiris.api.services.CompanyService;
import com.osiris.api.testutils.builders.ApiKeyBuilder;
import com.osiris.api.testutils.builders.CompanyBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created By francislagueu on 5/17/24
 */
public class AuthorizationInternalControllerTest extends BaseIntegrationTest {
    private static final String URL = CacheInternalApiController.BASE_URL;

    @Autowired
    private ApiKeyService apiKeyService;
    @Autowired private CompanyService companyService;

    @BeforeAll
    void init() {}

    @Test
    void return_401_IfApikeyIsNotFound() throws Exception {
        this.mockMvc
                .perform(delete(URL).header(AppHeaders.API_KEY_HEADER, random()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void return_401_IfApikeyCompanyHasWrongRole() throws Exception {
        final var company = this.companyService.create(CompanyBuilder.company());
        final var apiKey = this.apiKeyService.create(ApiKeyBuilder.apiKey(company));
        this.mockMvc
                .perform(delete(URL).header(AppHeaders.API_KEY_HEADER, apiKey.getKey()))
                .andExpect(status().isForbidden());

        final var management = this.companyService.create(CompanyBuilder.management());
        final var apiKeyManagement = this.apiKeyService.create(ApiKeyBuilder.apiKey(management));
        this.mockMvc
                .perform(delete(URL).header(AppHeaders.API_KEY_HEADER, apiKeyManagement.getKey()))
                .andExpect(status().isForbidden());
    }

    @Test
    void return_401_IfApikeyIsDisabled() throws Exception {
        final var internal = this.companyService.create(CompanyBuilder.internal());
        final var apiKey = this.apiKeyService.create(ApiKeyBuilder.apiKey(internal));
        this.apiKeyService.delete(apiKey.getId());
        this.mockMvc
                .perform(delete(URL).header(AppHeaders.API_KEY_HEADER, apiKey.getKey()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void return_200() throws Exception {
        final var internal = this.companyService.create(CompanyBuilder.internal());
        final var apiKey = this.apiKeyService.create(ApiKeyBuilder.apiKey(internal));
        this.mockMvc
                .perform(delete(URL).header(AppHeaders.API_KEY_HEADER, apiKey.getKey()))
                .andExpect(status().isOk());
    }
}
