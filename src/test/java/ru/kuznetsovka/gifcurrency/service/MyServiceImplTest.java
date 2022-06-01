package ru.kuznetsovka.gifcurrency.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kuznetsovka.gifcurrency.controller.ApiCurrencyClient;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class MyServiceImplTest {
    @Autowired
    ApiCurrencyClient apiClientCurrency;
    @Value("${app.feign.config.currency.app_id}")
    String appId;
    @Value("${app.feign.config.currency.base_currency}")
    String base;
    private final String CURRENCY = "RUB";
    private RateDto rate;
    private final String date = "2022-06-01";

    @BeforeAll
    public void init(){
        rate = new RateDto(CURRENCY,63.874903);
    }

    @Test
    void getRateUSDbyCurrencyYesterday() {
        RateDto rateAssert = apiClientCurrency.getRateUSDbyCurrencyYesterday(date,appId,base,CURRENCY).getRate();
        assertThat(rateAssert).isEqualByComparingTo(rate);
    }
}