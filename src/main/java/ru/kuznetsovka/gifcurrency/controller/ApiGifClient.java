package ru.kuznetsovka.gifcurrency.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsovka.gifcurrency.dto.data.MainGifDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@FeignClient(
        value = "${app.feign.config.gif.name}",
        url = "${app.feign.config.gif.url}",
        fallback = MainGifDto.class)
public interface ApiGifClient {
    /**
     * Method requests to the external API service
     * @param apiKey
     * @param tag
     * @return MainGifDto
     */
    @GetMapping(params = {"api_key", "tag"})
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    MainGifDto getGifRandom(@RequestParam(value = "api_key") String apiKey,
                            @RequestParam(value = "tag") String tag);
}
