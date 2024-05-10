package com.tnl.coindeskapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Data
@Builder
public class CurrencyDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    private String code;
    private String name;
}
