package com.tnl.coindeskapi.repository;

import com.tnl.coindeskapi.entity.CurrencyRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, UUID> {
    Page<CurrencyRate> findAllByCurrencyCode(String currencyCode, Pageable pageable);
}
