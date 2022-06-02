package ru.kuznetsovka.gifcurrency.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.gen5.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;
import ru.kuznetsovka.gifcurrency.service.MyService;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8080)
class GifControllerTest {
    @MockBean
    MyService myService;

    private final String CURRENCY = "RUB";
    private RateDto rate;
    @Value("${server.hostname}")
    private String host;
    @Value("${server.port}")
    private int port;

    @BeforeAll
    public void init(){
        WireMock.configureFor(host,port);
        rate = new RateDto(CURRENCY,63.874903);
    }

    //Выполняется только сегодня, 01.06.2022
    @Test
    void getJsonRateTodayFirst() {

        stubFor(get(urlPathMatching("/api/v1/check/RUB/today"))
                .atPriority(1)
                .willReturn(aResponse()
                        .withStatus(200)));
        verify(postRequestedFor(urlEqualTo("/api/v1/check/RUB/today"))
                .withHeader("Content-Type", equalTo("application/json")));
        Mockito.when(myService.getRateUSDbyCurrencyToday(CURRENCY).getRate()).thenReturn(rate);
    }

    @SneakyThrows
    @Test
    void getJsonRateYesterday() {
        stubFor(get(urlPathMatching("/api/v1/check/RUB/today"))
                .atPriority(1)
                .willReturn(aResponse()
                        .withStatus(200)));
        verify(postRequestedFor(urlEqualTo("/api/v1/check/RUB/today"))
                .withHeader("Content-Type", equalTo("application/json")));
    }
}