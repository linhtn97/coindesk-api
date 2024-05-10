package com.tnl.coindeskapi.initializr;


import com.tnl.coindeskapi.entity.Currency;
import com.tnl.coindeskapi.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initCurrency(CurrencyRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(Currency.builder().code("USD").name("United States Dollar").build()));
            log.info("Preloading " + repository.save(Currency.builder().code("GBP").name("British Pound Sterling").build()));
            log.info("Preloading " + repository.save(Currency.builder().code("EUR").name("Euro").build()));
        };
    }
}
