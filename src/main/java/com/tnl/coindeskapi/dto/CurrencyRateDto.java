package com.tnl.coindeskapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.sql.Timestamp;
import java.util.UUID;

@Value
@Jacksonized
@Builder
public class CurrencyRateDto {
    @JsonIgnore
    private UUID id;
    private String currencyCode;
    private String currencyName;
    private double rate;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Timestamp updatedTime;
}
