package com.tnl.coindeskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnl.coindeskapi.api.controller.CurrencyController;
import com.tnl.coindeskapi.dto.CurrencyDto;
import com.tnl.coindeskapi.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CurrencyService currencyService;

    @Test
    public void testCreateCurrency() throws Exception {
        CurrencyDto requestDto = CurrencyDto.builder()
                .code("USD")
                .name("Dollar")
                .build();
        CurrencyDto responseDto = CurrencyDto.builder()
                .id(0)
                .code("USD")
                .name("Dollar")
                .build();

        when(currencyService.create(requestDto)).thenReturn(responseDto);


        mockMvc.perform(post("/api/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.code").value("USD"))
                .andExpect(jsonPath("$.name").value("Dollar"));
    }

    @Test
    public void testUpdateCurrency() throws Exception {
        CurrencyDto requestDto = CurrencyDto.builder()
                .id(0)
                .code("USD")
                .name("Dollar 123")
                .build();
        CurrencyDto responseDto = CurrencyDto.builder()
                .id(0)
                .code("USD")
                .name("Dollar 123")
                .build();

        when(currencyService.update(requestDto)).thenReturn(responseDto);


        mockMvc.perform(put("/api/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.code").value("USD"))
                .andExpect(jsonPath("$.name").value("Dollar 123"));
    }

    @Test
    public void testGetCurrencyById() throws Exception {
        CurrencyDto responseDto = CurrencyDto.builder()
                .id(0)
                .code("USD")
                .name("Dollar 123")
                .build();

        when(currencyService.getById(0)).thenReturn(responseDto);


        mockMvc.perform(get("/api/v1/currency/{id}", 0)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.code").value("USD"))
                .andExpect(jsonPath("$.name").value("Dollar 123"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
