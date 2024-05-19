package com.osiris.api.responses.management;

import java.time.LocalDateTime;

/**
 * Created By francislagueu on 5/13/24
 */
public record ApiKeyManagementResponse(
        Long id,
        Long companyId,
        String name,
        String key,
        Boolean isActive,
        String createdBy,
        String updatedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
