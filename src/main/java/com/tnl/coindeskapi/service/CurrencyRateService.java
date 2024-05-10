package com.tnl.coindeskapi.service;

import com.tnl.coindeskapi.api.request.PagingRequest;
import com.tnl.coindeskapi.api.response.PageResponse;
import com.tnl.coindeskapi.dto.CurrencyRateDto;
import com.tnl.coindeskapi.entity.CurrencyRate;

import java.util.Map;

public interface CurrencyRateService {
    Map<String, CurrencyRateDto> fetchCoinDeskPrice();
    PageResponse<CurrencyRateDto> findAll(String currencyCode, PagingRequest req);

}
