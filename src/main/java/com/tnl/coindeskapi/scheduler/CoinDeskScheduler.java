package com.tnl.coindeskapi.scheduler;

import com.tnl.coindeskapi.dto.CurrencyRateDto;
import com.tnl.coindeskapi.mapper.CurrencyRateMapper;
import com.tnl.coindeskapi.model.CurrentPrice;
import com.tnl.coindeskapi.repository.CurrencyRateRepository;
import com.tnl.coindeskapi.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CoinDeskScheduler {
    private final CurrencyRateService currencyRateService;
    private final CurrencyRateRepository currencyRateRepository;

    private final CurrencyRateMapper currencyRateMapper;
    @Scheduled(fixedRate = 1000 * 5)
    public void fetchCoinDeskPrice(){
        currencyRateRepository.saveAll(currencyRateMapper.toEntities(currencyRateService.fetchCoinDeskPrice().values().stream().toList()));
    }

}
