package com.osiris.api.utils;

import com.osiris.api.entities.base.BaseEntity;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Created By francislagueu on 5/13/24
 */
@UtilityClass
public class LogUtils {
    public static final String NULL = "null";

    public static String logId(final BaseEntity entity) {
        return entity != null && entity.getId() != null ? entity.getId().toString() : StringUtils.EMPTY;
    }

    public static String logIds(@NonNull final List<BaseEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of().toString();
        }
        return entities.stream()
                .map(e -> e.getId() != null ? e.getId().toString() : NULL)
                .toList()
                .toString();
    }
}
