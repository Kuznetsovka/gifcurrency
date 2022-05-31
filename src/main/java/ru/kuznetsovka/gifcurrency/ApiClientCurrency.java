package ru.kuznetsovka.gifcurrency;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsovka.gifcurrency.rate.RateDtoWrapper;

@FeignClient(value = "${app.feign.config.currency.name}", url = "${app.feign.config.currency.url}", fallback = RateDtoWrapper.class)
public interface ApiClientCurrency {

    @GetMapping(value = "/historical/{yesterday}.json", consumes = "application/json", produces = "application/json", params = {"app_id", "base", "symbols"})
    RateDtoWrapper getRateUSDbyCurrencyYesterday(@PathVariable(value = "yesterday") String yesterday,
                                                 @RequestParam(value = "app_id") String appId,
                                                 @RequestParam(value = "base") String base,
                                                 @RequestParam(value = "symbols") String symbols);

    @GetMapping(value = "/latest.json", consumes = "application/json", produces = "application/json", params = {"app_id", "base", "symbols"})
    RateDtoWrapper getRateUSDbyCurrencyToday(@RequestParam(value = "app_id") String appId,
                                             @RequestParam(value = "base") String base,
                                             @RequestParam(value = "symbols") String symbols);
}

