package ru.kuznetsovka.gifcurrency;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${app.feign.config.gif.name}", url = "${app.feign.config.gif.url}", fallback = Date.class)
public interface GifClient {
    @GetMapping(consumes = "application/json",  params = {"api_key", "tag"})
    Date getGifRandom(@RequestParam(value = "api_key") String apiKey,
                        @RequestParam(value = "tag") String tag);
}
