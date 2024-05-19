package com.osiris.api.controllers.management;

import com.osiris.api.constants.AppUrls;
import com.osiris.api.controllers.management.base.BaseManagementController;
import com.osiris.api.entities.ApiKey;
import com.osiris.api.mappers.ApiKeyMapper;
import com.osiris.api.requests.management.CreateApiKeyManagementRequest;
import com.osiris.api.requests.management.UpdateApiKeyManagementRequest;
import com.osiris.api.responses.management.ApiKeyManagementResponse;
import com.osiris.api.services.ApiKeyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By francislagueu on 5/15/24
 */
@Slf4j
@RestController
@RequestMapping(ApiKeyManagementController.BASE_URL)
@RequiredArgsConstructor
public class ApiKeyManagementController extends BaseManagementController<
        ApiKey,
        CreateApiKeyManagementRequest,
        UpdateApiKeyManagementRequest,
        ApiKeyManagementResponse> {
    public static final String BASE_URL = AppUrls.MANAGEMENT + "/api-keys";

    @Getter
    private final ApiKeyService service;
    @Getter private final ApiKeyMapper mapper;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable("id") final Long id) {
        log.info("[request] inactive {} '{}'", ApiKey.TABLE_NAME, id);
        this.service.inactivate(id);
    }
}
