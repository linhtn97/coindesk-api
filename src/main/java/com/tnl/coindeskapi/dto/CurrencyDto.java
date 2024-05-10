package com.tnl.coindeskapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Data
@Builder
public class CurrencyDto {
    @JsonIgnore
    private int id;
    private String code;
    private String name;
}
