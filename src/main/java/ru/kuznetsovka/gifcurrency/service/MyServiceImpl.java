package ru.kuznetsovka.gifcurrency.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kuznetsovka.gifcurrency.controller.ApiClientCurrency;
import ru.kuznetsovka.gifcurrency.controller.ApiGifClient;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDtoWrapper;

import java.util.HashMap;
import java.util.Map;

import static ru.kuznetsovka.gifcurrency.Utils.getYesterday;

@Service
@Log4j2
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
    Map<String, Double> yesterdayRateMap = new HashMap<>();

    @Override
    public String difBetweenYesterdayAndTodayRate(String currency) {
        RateDto rateYesterday = getRateUSDbyCurrencyYesterday(currency);
        log.info("Get rateYesterday:" + rateYesterday);
        RateDto rateToday = getRateUSDbyCurrencyToday(currency).getRate();
        log.info("Get rateToday:" + rateToday);
        return getUrlGifByDifRates(rateYesterday, rateToday);
    }

    @Override
    public String getUrlGifByDifRates(RateDto rateYesterday, RateDto rateToday) {
        log.info("Request gif by rateYesterday and rateToday");
        return (rateYesterday.compareTo(rateToday) <= 0) ?
                apiGifClient.getGifRandom(apiKey, tagRich).getDateDto().getUrl() :
                apiGifClient.getGifRandom(apiKey, tagBroke).getDateDto().getUrl();
    }

    @Override
    public RateDtoWrapper getRateUSDbyCurrencyToday(String currency) {
        return apiClient.getRateUSDbyCurrencyToday(appId, base, currency);
    }

    @Override
    public String getGifRandom(String apiKey, String tag) {
        return apiGifClient.getGifRandom(apiKey, tag).getDateDto().getUrl();
    }

    @Override
    public RateDto getRateUSDbyCurrencyYesterday(String currency) {
        return (yesterdayRateMap.get(currency) != null) ?
                new RateDto(currency, yesterdayRateMap.get(currency)) :
                apiClient.getRateUSDbyCurrencyYesterday(getYesterday(), appId, base, currency).getRate();
    }


}
