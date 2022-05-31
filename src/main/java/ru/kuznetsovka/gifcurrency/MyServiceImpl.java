package ru.kuznetsovka.gifcurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kuznetsovka.gifcurrency.rate.RateDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private ApiClientCurrency apiClient;
    @Autowired
    private ApiGifClient apiGifClient;
    @Value("${app.feign.config.currency.app_id}")
    String appId;
    @Value("${app.feign.config.currency.base_currency}")
    String base;
    @Value("${app.feign.config.gif.api_key}")
    String apiKey;
    @Value("${app.feign.config.gif.tag_rich}")
    String tagRich;
    @Value("${app.feign.config.gif.tag_broke}")
    String tagBroke;
    Map<String, Double> yesterdayRate = new HashMap<>();

    @Override
    public String difBetweenYesterdayAndTodayRate(String currency) {
        String yesterday = getYesterday();
        RateDto rateYesterday = getRateDtoByDay(currency, yesterday);
        RateDto rateToday = apiClient.getRateUSDbyCurrencyToday(appId, base, currency).getRates();
        return getUrlGifByDifRates(rateYesterday, rateToday);
    }

    @Override
    public String getUrlGifByDifRates(RateDto rateYesterday, RateDto rateToday) {
        return (rateYesterday.compareTo(rateToday) <= 0) ?
                apiGifClient.getGifRandom(apiKey, tagRich).getDateDto().getUrl() :
                apiGifClient.getGifRandom(apiKey, tagBroke).getDateDto().getUrl();
    }

    @Override
    public RateDto getRateDtoByDay(String currency, String yesterdayStr) {
        return (yesterdayRate.get(currency) != null) ?
                new RateDto(currency, yesterdayRate.get(currency)) :
                apiClient.getRateUSDbyCurrencyYesterday(yesterdayStr, appId, base, currency).getRates();
    }

    // Можно было бы убрать в статический класс, но пока такой метод 1 нет смысла
    private String getYesterday() {
        LocalDate yesterday = LocalDate.now().minusDays(1L);
        return yesterday.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
