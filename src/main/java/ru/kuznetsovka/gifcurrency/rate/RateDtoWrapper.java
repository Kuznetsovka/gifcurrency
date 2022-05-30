package ru.kuznetsovka.gifcurrency.rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class RateDtoWrapper {
    @JsonProperty("rates")
    @JsonDeserialize(using = RatesDeserializer.class)
    private ArrayList<RateDto> rates;
}