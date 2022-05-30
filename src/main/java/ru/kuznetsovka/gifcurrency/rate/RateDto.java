package ru.kuznetsovka.gifcurrency.rate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateDto implements Comparable<RateDto> {
    private String code;
    private Double rate;

    @Override
    public int compareTo(RateDto o) {
        return this.rate.compareTo(o.rate);
    }
}
