package com.osiris.api.mappers.base;

import com.osiris.api.mappers.annotations.ToEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;

/**
 * Created By francislagueu on 5/13/24
 */
public interface ManagementBaseMapper <E, C, U, R>{
    @ToEntity
    E toEntity(C request);

    @ToEntity
    E update(U request, @MappingTarget E entity);

    @ToEntity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    E patch(U request, @MappingTarget E entity);

    R toManagementResponse(E entity);

    Collection<R> toManagementResponse(Collection<E> entity);
}
