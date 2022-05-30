package ru.kuznetsovka.gifcurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kuznetsovka.gifcurrency.rate.RateDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private ApiClientCurrency apiClient;
    @Autowired
    private GifClient gifClient;
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

    @Override
    public String difBetweenYesterdayAndTodayRate(String currency) {
        Date gif;
        LocalDate yesterday = LocalDate.now().minusDays(1L);
        String yesterdayStr = yesterday.format(DateTimeFormatter.ISO_LOCAL_DATE);
        RateDto rateYesterday = apiClient.getRateUSDbyCurrencyYesterday(yesterdayStr,appId,base,currency).getRates().get(0);
        RateDto rateToday = apiClient.getRateUSDbyCurrencyToday(appId,base,currency).getRates().get(0);
        if(rateYesterday.compareTo(rateToday) >= 0)
            gif = gifClient.getGifRandom(apiKey,tagRich);
        else
            gif = gifClient.getGifRandom(apiKey,tagBroke);
        return gif.getUrl();
    }

}
