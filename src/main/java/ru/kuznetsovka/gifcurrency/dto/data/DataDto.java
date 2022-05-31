package ru.kuznetsovka.gifcurrency.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDto {
    @JsonProperty(value = "url")
    private String url;
}
