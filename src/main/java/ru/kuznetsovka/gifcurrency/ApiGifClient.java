package ru.kuznetsovka.gifcurrency;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${app.feign.config.gif.name}", url = "${app.feign.config.gif.url}", fallback = MainGifDto.class)
public interface ApiGifClient {
    @GetMapping(consumes = "application/json", produces = "application/json",  params = {"api_key", "tag"})
    MainGifDto getGifRandom(@RequestParam(value = "api_key") String apiKey,
                        @RequestParam(value = "tag") String tag);
}
