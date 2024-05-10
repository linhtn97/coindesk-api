package com.tnl.coindeskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnl.coindeskapi.api.controller.CurrencyRateController;
import com.tnl.coindeskapi.dto.CurrencyRateDto;
import com.tnl.coindeskapi.service.CurrencyRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyRateController.class)
public class CurrencyRateControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyRateService currencyRateService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCoindesk() throws Exception {
        Map<String, CurrencyRateDto> response = new HashMap<>();
        response.put("USD", CurrencyRateDto.builder()
                .currencyCode("USD")
                .currencyName("Dollar")
                .rate(123.123456)
                .updatedTime(Timestamp.valueOf(LocalDateTime.of(1990, 1, 1, 0, 0, 0)))
                .build());
        when(currencyRateService.fetchCoinDeskPrice()).thenReturn(response);

        mockMvc.perform(get("/api/v1/rate/coin-desk")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.USD.currencyCode").value("USD"))
                .andExpect(jsonPath("$.USD.currencyName").value("Dollar"))
                .andExpect(jsonPath("$.USD.rate").value(123.123456))
                .andExpect(jsonPath("$.USD.updatedTime").value("1989/12/31 17:00:00"));

    }
}
