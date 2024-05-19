package com.osiris.api.services;

import com.osiris.api.entities.Company;
import com.osiris.api.exceptions.ResourceNotFoundException;
import com.osiris.api.repositories.CompanyRepository;
import com.osiris.api.services.base.BaseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CompanyService extends BaseService<Company> {
    @Getter
    private final CompanyRepository repository;

    public Optional<Company> findBySlugOptional(final String slug) {
        log.debug("[retrieving] company with slug '{}'", slug);
        if (StringUtils.isBlank(slug)) {
            return Optional.empty();
        }
        return this.repository.findBySlug(slug);
    }

    public Company findBySlug(final String slug) {
        return this.findBySlugOptional(slug)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        String.format("company with slug '%s' not found", slug)));
    }
}
