package com.osiris.api.infra.auth.providers;

import com.osiris.api.entities.ApiKey;
import com.osiris.api.entities.Company;
import com.osiris.api.services.ApiKeyService;
import com.osiris.api.services.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    private static final String LOG_NAME = "ApiKeyAuthProvider";

    @Autowired
    private ApiKeyService apiKeyService;
    @Autowired private CompanyService companyService;

    @Override
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {

        final String apiKeyInRequest = (String) authentication.getPrincipal();

        if (StringUtils.isBlank(apiKeyInRequest)) {
            log.info("[{}] api-key is not defined on request, returning 401", LOG_NAME);
            throw new InsufficientAuthenticationException("api-key is not defined on request");
        } else {
            log.debug("[{}] start searching for api-key '{}'", LOG_NAME, apiKeyInRequest);
            final Optional<ApiKey> apiKeyOptional = this.apiKeyService.findByKeyOptional(apiKeyInRequest);

            if (apiKeyOptional.isPresent()) {
                final ApiKey apiKey = apiKeyOptional.get();
                final Company company = this.companyService.findById(apiKey.getCompanyId());
                log.debug(
                        "[{}] api-key '{}' found with authorities '{}'",
                        LOG_NAME,
                        apiKeyInRequest,
                        company.getGrantedAuthoritiesFromCompanyType());

                final ApiKeyAuthentication.ApiKeyDetails apiKeyDetails =
                        ApiKeyAuthentication.ApiKeyDetails.builder()
                                .id(apiKey.getId())
                                .companySlug(company.getSlug())
                                .email(company.getEmail())
                                .isInternal(Boolean.TRUE.equals(company.getIsInternal()))
                                .isPlatform(Boolean.TRUE.equals(company.getIsPlatform()))
                                .build();

                return new ApiKeyAuthentication(
                        apiKey.getKey(), true, apiKeyDetails, company.getGrantedAuthoritiesFromCompanyType());
            }

            log.info("[{}] api-key '{}' not found, returning 401", LOG_NAME, apiKeyInRequest);
            throw new BadCredentialsException("invalid api-key");
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return ApiKeyAuthentication.class.isAssignableFrom(authentication);
    }
}
