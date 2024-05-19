package com.osiris.api.responses.shared;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * Created By francislagueu on 5/13/24
 */
public record ApiListPaginationSimple<T>(PaginationMeta<T> meta, Collection<T> data) {
    public ApiListPaginationSimple(final Page<T> page) {
        this(new PaginationMeta<>(page), page.getContent());
    }

    public static <T> ApiListPaginationSimple<T> of(final Page<T> page) {
        return new ApiListPaginationSimple<>(page);
    }

    public record PaginationMeta<T>(Integer currentPage, Integer pageSize, String sortedBy) {

        public PaginationMeta(final Page<T> page) {
            this(
                    page.getNumber(),
                    page.getSize(),
                    page.getSort().isSorted() ? page.getSort().toString() : StringUtils.EMPTY);
        }
    }
}
