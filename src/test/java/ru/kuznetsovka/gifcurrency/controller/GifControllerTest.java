package ru.kuznetsovka.gifcurrency.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;
import ru.kuznetsovka.gifcurrency.service.MyService;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class GifControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MyService myService;

    private final String CURRENCY = "RUB";
    private RateDto rate;

    @BeforeAll
    public void init(){
        rate = new RateDto(CURRENCY,63.874903);
    }

    //Выполняется только сегодня, 01.06.2022
    @Test
    void getJsonRateTodayFirst() {
        Mockito.when(myService.getRateUSDbyCurrencyToday(CURRENCY).getRate()).thenReturn(rate);
    }

    @SneakyThrows
    @Test
    void getJsonRateTodaySecond() {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/check/" + CURRENCY + "/today")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.rate").value(CURRENCY));
    }

    @SneakyThrows
    @Test
    void getJsonRateYesterday() {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/check/" + CURRENCY + "/yesterday")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.rate").value(CURRENCY));
    }
}