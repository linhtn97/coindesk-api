package com.tnl.coindeskapi.service.impl;

import com.tnl.coindeskapi.api.request.PagingRequest;
import com.tnl.coindeskapi.api.response.PageResponse;
import com.tnl.coindeskapi.dto.CurrencyDto;
import com.tnl.coindeskapi.entity.Currency;
import com.tnl.coindeskapi.mapper.CurrencyMapper;
import com.tnl.coindeskapi.repository.CurrencyRepository;
import com.tnl.coindeskapi.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public CurrencyDto create(CurrencyDto req) {
        return currencyMapper.toDto(currencyRepository.save(currencyMapper.toEntity(req)));
    }

    @Override
    public CurrencyDto update(CurrencyDto req) {
        Currency currency = currencyRepository.findById(req.getId()).orElseThrow();
        currencyMapper.fillToEntity(currency, req);
        return currencyMapper.toDto(currencyRepository.save(currency));
    }

    @Override
    public CurrencyDto getById(int currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow();
        return currencyMapper.toDto(currency);
    }

    @Override
    public void deleteById(int id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public PageResponse<CurrencyDto> findAll(PagingRequest req) {
        Sort sort = Sort.by(Sort.Order.asc("code"));
        Pageable pageable = PageRequest.of(req.getPage(), req.getPageSize(),sort);
        return currencyMapper.toPageResults(currencyRepository.findAll(pageable));
    }
}
