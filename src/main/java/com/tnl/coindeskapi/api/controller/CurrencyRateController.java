package com.tnl.coindeskapi.api.controller;

import com.tnl.coindeskapi.api.request.PagingRequest;
import com.tnl.coindeskapi.api.response.PageResponse;
import com.tnl.coindeskapi.dto.CurrencyDto;
import com.tnl.coindeskapi.dto.CurrencyRateDto;
import com.tnl.coindeskapi.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/rate")
@RequiredArgsConstructor
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;
    @GetMapping("/coin-desk")
    ResponseEntity<Map<String, CurrencyRateDto>> getCoindesk(){
        return ResponseEntity.ok(currencyRateService.fetchCoinDeskPrice());
    }

    @GetMapping()
    ResponseEntity<PageResponse<CurrencyRateDto>> getAll(String currencyCode, PagingRequest req) {
        return ResponseEntity.ok(currencyRateService.findAll(currencyCode, req));
    }
}
