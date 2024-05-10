package com.tnl.coindeskapi.mapper;

import com.tnl.coindeskapi.dto.CurrencyDto;
import com.tnl.coindeskapi.entity.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CurrencyMapper extends BaseMapper<Currency, CurrencyDto> {
    @Override
    Currency toEntity(CurrencyDto currencyDto);

    @Override
    CurrencyDto toDto(Currency currency);

    @Override
    void fillToEntity(@MappingTarget Currency currency, CurrencyDto currencyDto);
}
