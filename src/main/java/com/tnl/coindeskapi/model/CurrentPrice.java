package com.tnl.coindeskapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CurrentPrice {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    @JsonAlias("rate_float")
    private String rateFloat;
    private Timestamp updatedTime;
}
