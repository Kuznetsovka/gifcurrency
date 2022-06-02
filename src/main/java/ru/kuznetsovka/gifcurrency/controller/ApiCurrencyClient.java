package ru.kuznetsovka.gifcurrency.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDtoWrapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@FeignClient(
        value = "${app.feign.config.currency.name}",
        url = "${app.feign.config.currency.url}",
        fallback = RateDtoWrapper.class)
public interface ApiCurrencyClient {
    /**
     * Method requests to the external API service to get json Rate yesterday
     * @param yesterday
     * @param appId
     * @param base
     * @param currency
     * @return RateDtoWrapper
     */
    @GetMapping(value = "/historical/{yesterday}.json", params = {"app_id", "base", "symbols"})
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Cacheable(cacheNames = "yesterday-cache")
    RateDtoWrapper getRateUSDbyCurrencyYesterday(@PathVariable(value = "yesterday") String yesterday,
                                                 @RequestParam(value = "app_id") String appId,
                                                 @RequestParam(value = "base") String base,
                                                 @RequestParam(value = "symbols") String currency);
    /**
     * Method requests to the external API service to get json Rate today
     * @param appId
     * @param base
     * @param currency
     * @return RateDtoWrapper
     */
    @GetMapping(value = "/latest.json", params = {"app_id", "base", "symbols"})
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    RateDtoWrapper getRateUSDbyCurrencyToday(@RequestParam(value = "app_id") String appId,
                                             @RequestParam(value = "base") String base,
                                             @RequestParam(value = "symbols") String currency);
}

