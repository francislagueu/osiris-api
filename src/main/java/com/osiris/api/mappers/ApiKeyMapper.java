package com.osiris.api.mappers;

import com.osiris.api.entities.ApiKey;
import com.osiris.api.mappers.base.ManagementBaseMapper;
import com.osiris.api.requests.management.CreateApiKeyManagementRequest;
import com.osiris.api.requests.management.UpdateApiKeyManagementRequest;
import com.osiris.api.responses.management.ApiKeyManagementResponse;
import org.mapstruct.Mapper;

/**
 * Created By francislagueu on 5/13/24
 */
@Mapper(componentModel = "spring")
public interface ApiKeyMapper extends ManagementBaseMapper<
        ApiKey,
        CreateApiKeyManagementRequest,
        UpdateApiKeyManagementRequest,
        ApiKeyManagementResponse> {

    @Override
    ApiKey toEntity(CreateApiKeyManagementRequest request);

    @Override
    ApiKeyManagementResponse toManagementResponse(ApiKey entity);
}
