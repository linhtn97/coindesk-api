package com.tnl.coindeskapi.mapper;

import com.tnl.coindeskapi.dto.CurrencyRateDto;
import com.tnl.coindeskapi.entity.CurrencyRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyRateMapper extends BaseMapper<CurrencyRate, CurrencyRateDto>{
}
