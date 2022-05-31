package ru.kuznetsovka.gifcurrency.dto.rate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RateDto implements Comparable<RateDto> {
    private String code;
    private Double rate;

    @Override
    public int compareTo(RateDto o) {
        return this.rate.compareTo(o.rate);
    }
}
