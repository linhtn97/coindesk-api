package com.tnl.coindeskapi.api.controller;

import com.tnl.coindeskapi.api.request.PagingRequest;
import com.tnl.coindeskapi.api.response.PageResponse;
import com.tnl.coindeskapi.dto.CurrencyDto;
import com.tnl.coindeskapi.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PostMapping
    ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto req) {
        return ResponseEntity.ok(currencyService.create(req));
    }

    @PutMapping
    ResponseEntity<CurrencyDto> update(@RequestBody CurrencyDto req) {
        return ResponseEntity.ok(currencyService.update(req));
    }

    @DeleteMapping("/{id}")
    void update(@PathVariable int id) {
        currencyService.deleteById(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<CurrencyDto> get(@PathVariable int id) {
        return ResponseEntity.ok(currencyService.getById(id));
    }

    @GetMapping
    ResponseEntity<PageResponse<CurrencyDto>> getAll(PagingRequest req) {
        return ResponseEntity.ok(currencyService.findAll(req));
    }
}
