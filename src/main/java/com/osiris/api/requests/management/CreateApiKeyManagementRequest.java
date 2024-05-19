package com.osiris.api.requests.management;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Created By francislagueu on 5/13/24
 */
public record CreateApiKeyManagementRequest(@NotNull Long companyId, @NotBlank String name) {}

