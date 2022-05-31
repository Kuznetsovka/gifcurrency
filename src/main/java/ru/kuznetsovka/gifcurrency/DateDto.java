package ru.kuznetsovka.gifcurrency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DateDto {
    @JsonProperty(value = "url")
    private String url;
}
