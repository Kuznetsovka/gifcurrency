package ru.kuznetsovka.gifcurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Date {
    @JsonProperty
    private String url;
}
