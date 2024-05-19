package com.osiris.api.services;

import com.osiris.api.entities.ApiKey;
import com.osiris.api.exceptions.ResourceNotFoundException;
import com.osiris.api.repositories.ApiKeyRepository;
import com.osiris.api.services.base.BaseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.osiris.api.utils.CryptoUtils.randomKey;
import static java.lang.String.format;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ApiKeyService extends BaseService<ApiKey> {
    @Getter
    private final ApiKeyRepository repository;

    @Override
    protected void activitiesBeforeCreateEntity(final ApiKey entity) {
        entity.setIsActive(true);
        entity.setKey(randomKey(18));
    }

    public Optional<ApiKey> findByKeyOptional(final String key) {
        log.debug("[retrieving] apiKey");
        return this.repository.findByKeyAndIsActive(key, true);
    }

    public ApiKey findFirstByCompanyIdAndIsActive(final Long companyId) {
        log.debug("[retrieving] apiKey with companyId {}", companyId);
        return this.repository.findFirstByCompanyIdAndIsActive(companyId, true);
    }

    @Transactional
    public void inactivate(final Long id) {
        log.info("[inactivating] apiKey with id '{}'", id);

        final Optional<ApiKey> apiKeyOptional = this.getRepository().findById(id);
        if (apiKeyOptional.isEmpty()) {
            throw new ResourceNotFoundException(format("apiKey '%s' not found", id));
        }

        final ApiKey entity = apiKeyOptional.get();
        entity.setIsActive(false);
        this.update(entity);
    }
}
