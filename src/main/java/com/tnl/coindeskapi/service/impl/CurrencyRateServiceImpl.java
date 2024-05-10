package com.tnl.coindeskapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnl.coindeskapi.api.request.PagingRequest;
import com.tnl.coindeskapi.api.response.PageResponse;
import com.tnl.coindeskapi.dto.CurrencyRateDto;
import com.tnl.coindeskapi.entity.CurrencyRate;
import com.tnl.coindeskapi.mapper.CurrencyRateMapper;
import com.tnl.coindeskapi.model.CurrentPrice;
import com.tnl.coindeskapi.repository.CurrencyRateRepository;
import com.tnl.coindeskapi.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {
    private final ObjectMapper objectMapper;
    private final CurrencyRateMapper currencyRateMapper;
    private final CurrencyRateRepository currencyRateRepository;

    @Override
    public Map<String, CurrencyRateDto> fetchCoinDeskPrice() {
        Map<String, CurrencyRateDto> currencyRateDtos = new HashMap<>();
        try {

            JsonNode jsonNode = WebClient.builder()
                    .baseUrl("https://api.coindesk.com/v1/bpi")
                    .build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/currentprice.json")
                            .build()
                    )
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve()
                    .bodyToMono(JsonNode.class).block();

            Map<String, Object> bpiObject = objectMapper.treeToValue(jsonNode.get("bpi"), Map.class);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss z");
            Timestamp updatedTime = new Timestamp(dateFormat.parse(objectMapper.convertValue(jsonNode.get("time").get("updated"), String.class)).getTime());

            for (Map.Entry<String, Object> entry : bpiObject.entrySet()) {
                CurrentPrice currentPrice = objectMapper.convertValue(entry.getValue(), CurrentPrice.class);

                currencyRateDtos.put(
                        entry.getKey(),
                        CurrencyRateDto.builder()
                                .currencyCode(currentPrice.getCode())
                                .currencyName(currentPrice.getDescription())
                                .rate(Double.parseDouble(currentPrice.getRateFloat()))
                                .updatedTime(updatedTime)
                        .build()
                );
            }
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing Json Object: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }
        return currencyRateDtos;
    }

    @Override
    public PageResponse<CurrencyRateDto> findAll(String currencyCode, PagingRequest req) {
        Sort sort = Sort.by(Sort.Order.desc("updatedTime"));
        Pageable pageable = PageRequest.of(req.getPage(), req.getPageSize(),sort);
        return currencyRateMapper.toPageResults(currencyRateRepository.findAllByCurrencyCode(currencyCode,pageable));
    }


}
