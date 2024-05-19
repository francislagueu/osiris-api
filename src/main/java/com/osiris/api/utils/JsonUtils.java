package com.osiris.api.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created By francislagueu on 5/13/24
 */
@Slf4j
@UtilityClass
public class JsonUtils {
    private static final String SERIALIZATION_ERROR_MESSAGE =
            "Something went wrong during serialization/deserialization";

    public static <T> T deserializeFromCamelCase(final String content, final Class<T> valueType) {
        return getMapperSerialize(content, valueType, false);
    }

    public static <T> T deserializeFromSnakeCase(final String content, final Class<T> valueType) {
        return getMapperSerialize(content, valueType, true);
    }

    public static String serializeToCamelCase(final Object content) {
        try {
            return new ObjectMapper()
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    // Note: Force jackson to only serialize field and not getters.
                    .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .setPropertyNamingStrategy(new PropertyNamingStrategies.LowerCamelCaseStrategy())
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(content);
        } catch (final Exception ex) {
            log.error(SERIALIZATION_ERROR_MESSAGE, ex);
            throw new IllegalArgumentException(ex);
        }
    }

    public static String serializeToSnakeCase(final Object content) {
        try {
            return new ObjectMapper()
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    // Note: Force jackson to only serialize field and not getters.
                    .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy())
                    .registerModule(new JavaTimeModule())
                    .writeValueAsString(content);
        } catch (final Exception ex) {
            log.error(SERIALIZATION_ERROR_MESSAGE, ex);
            throw new IllegalArgumentException(ex);
        }
    }

    private static <T> T getMapperSerialize(
            final String content, final Class<T> valueType, final boolean fromSnakeCase) {
        try {

            if (StringUtils.isBlank(content)) {
                return valueType.getDeclaredConstructor().newInstance();
            }

            return new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .setPropertyNamingStrategy(
                            fromSnakeCase
                                    ? new PropertyNamingStrategies.SnakeCaseStrategy()
                                    : new PropertyNamingStrategies.LowerCamelCaseStrategy())
                    .registerModule(new JavaTimeModule())
                    .readValue(content, valueType);
        } catch (final Exception ex) {
            log.warn(SERIALIZATION_ERROR_MESSAGE, ex);
            throw new IllegalArgumentException(ex);
        }
    }
}
