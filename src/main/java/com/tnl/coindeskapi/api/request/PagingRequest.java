package com.tnl.coindeskapi.api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@Getter
public class PagingRequest {
    @Min(0)
    int page;
    @Min(10)
    @Max(50)
    int pageSize;
}

