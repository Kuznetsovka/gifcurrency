package ru.kuznetsovka.gifcurrency.rate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateDto implements Comparable<RateDto> {
    private String code;
    private Double rate;

    @Override
    public int compareTo(RateDto o) {
        return this.rate.compareTo(o.rate);
    }
}
