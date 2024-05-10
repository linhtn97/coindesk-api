package com.tnl.coindeskapi.mapper;

import com.tnl.coindeskapi.api.response.PageResponse;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public interface BaseMapper<E, D> {
    D toDto(E e);

    E toEntity(D d);

    void fillToEntity(@MappingTarget E e, D d);

    default Set<E> toEntities(Set<D> ds) {
        return ds.stream().map(this::toEntity).collect(toSet());
    }

    default Set<D> toDtos(Set<E> es) {
        return es.stream().map(this::toDto).collect(toSet());
    }

    default List<E> toEntities(List<D> ds) {
        return ds.stream().map(this::toEntity).collect(toList());
    }

    default List<D> toDtos(List<E> es) {
        return es.stream().map(this::toDto).collect(toList());
    }

    default PageResponse<D> toPageResults(Page<E> page) {
        return PageResponse.<D>builder()
                .page(page.getNumber())
                .pageSize(page.getSize())
                .hasPrevious(page.hasPrevious())
                .hasNext(page.hasNext())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .list(page.getContent().stream().map(this::toDto).collect(toList()))
                .build();
    }
}
