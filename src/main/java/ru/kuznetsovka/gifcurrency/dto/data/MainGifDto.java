package ru.kuznetsovka.gifcurrency.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainGifDto {
    @JsonProperty(value = "data")
    private DataDto dateDto;
}
