package ru.kuznetsovka.gifcurrency.service;

import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDtoWrapper;

public interface MyService {
    String difBetweenYesterdayAndTodayRate(String currency);

    RateDtoWrapper getRateUSDbyCurrencyToday(String currency);

    String getUrlGifByDifRates(RateDto rateYesterday, RateDto rateToday);

    String getGifRandom(String apiKey, String tag);

    RateDto getRateUSDbyCurrencyYesterday(String currency);
}
