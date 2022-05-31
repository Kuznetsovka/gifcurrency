package ru.kuznetsovka.gifcurrency;

import ru.kuznetsovka.gifcurrency.rate.RateDto;

public interface MyService {
    String difBetweenYesterdayAndTodayRate(String currency);

    String getUrlGifByDifRates(RateDto rateYesterday, RateDto rateToday);

    RateDto getRateDtoByDay(String currency, String yesterdayStr);
}
