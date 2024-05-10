package com.tnl.coindeskapi.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Value
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse<T> {
    int page;
    int pageSize;
    long totalItems;
    int totalPages;
    boolean hasPrevious;
    boolean hasNext;
    List<T> list;
}
