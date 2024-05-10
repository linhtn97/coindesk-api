package com.tnl.coindeskapi.service;

import com.tnl.coindeskapi.api.request.PagingRequest;
import com.tnl.coindeskapi.api.response.PageResponse;
import com.tnl.coindeskapi.dto.CurrencyDto;

public interface CurrencyService {
    CurrencyDto create(CurrencyDto req);
    CurrencyDto update(CurrencyDto req);
    CurrencyDto getById(int id);
    void deleteById(int id);
    PageResponse<CurrencyDto> findAll(PagingRequest req);

}
