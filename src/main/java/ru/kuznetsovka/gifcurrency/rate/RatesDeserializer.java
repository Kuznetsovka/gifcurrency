package ru.kuznetsovka.gifcurrency.rate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class RatesDeserializer
        extends JsonDeserializer<List<RateDto>> {

    @Override
    public List<RateDto> deserialize(JsonParser p, DeserializationContext context) throws IOException {
        List<RateDto> rates = new ArrayList<>();
        while (true) {
            String code = p.nextFieldName();
            if (isNull(code)) {
                break;
            }
            JsonToken token = p.nextValue();
            if (token.isNumeric()) {
                RateDto rate = new RateDto();
                rate.setCode(code);
                rate.setRate(p.getDoubleValue());
                rates.add(rate);
            }
        }
        return rates;
    }
}