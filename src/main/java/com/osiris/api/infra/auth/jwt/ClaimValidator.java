package com.osiris.api.infra.auth.jwt;

import com.osiris.api.entities.Company;
import com.osiris.api.services.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;

import java.util.Optional;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class ClaimValidator {
    private final CompanyService companyService;

    /*
     * Prevent user without company to access the API
     * */
    @Bean
    OAuth2TokenValidator<Jwt> companySlugValidator() {
        return new JwtClaimValidator<String>(
                "company_slug",
                slug -> {
                    final Optional<Company> companyOptional = this.companyService.findBySlugOptional(slug);
                    if (companyOptional.isEmpty()) {
                        log.warn("[companySlugValidator] company with slug {} not found", slug);
                    }
                    return companyOptional.isPresent();
                });
    }
}
