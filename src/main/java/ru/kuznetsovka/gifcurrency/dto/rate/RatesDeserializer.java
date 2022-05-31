package ru.kuznetsovka.gifcurrency.dto.rate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import static java.util.Objects.isNull;

public class RatesDeserializer
        extends JsonDeserializer<RateDto> {

    @Override
    public RateDto deserialize(JsonParser p, DeserializationContext context) throws IOException {
        RateDto rate = null;
        while (true) {
            String code = p.nextFieldName();
            if (isNull(code)) {
                break;
            }
            JsonToken token = p.nextValue();
            if (token.isNumeric()) {
                rate = new RateDto(code, p.getDoubleValue());
            }
        }
        return rate;
    }
}