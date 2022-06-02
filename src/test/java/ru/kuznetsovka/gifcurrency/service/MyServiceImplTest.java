package ru.kuznetsovka.gifcurrency.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kuznetsovka.gifcurrency.controller.ApiCurrencyClient;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class MyServiceImplTest {
    @Autowired
    ApiCurrencyClient apiCurrencyClient;
    @Value("${app.feign.config.currency.app_id}")
    String appId;
    @Value("${app.feign.config.currency.base_currency}")
    String base;
    private final static String CURRENCY = "RUB";
    private RateDto rate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        rate = new RateDto(CURRENCY,63.499993);
    }

    @Test
    void getRateUSDbyCurrencyYesterdayFirst() {
        String date = "2022-06-01";
        RateDto rateAssert = apiCurrencyClient.getRateUSDbyCurrencyYesterday(date,appId,base,CURRENCY).getRate();
        assertThat(rateAssert).isEqualByComparingTo(rate);
    }

}