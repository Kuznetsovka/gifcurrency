package ru.kuznetsovka.gifcurrency.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsovka.gifcurrency.dto.rate.RateDto;
import ru.kuznetsovka.gifcurrency.service.MyService;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/")
@Log4j2
public class GifController {
    @Autowired
    private MyService myService;

    @GetMapping(value = "/check/{currency}")
    public ResponseEntity<Void> getGif(@PathVariable (required = false) String currency) {
        log.info("Get request /check/{currency}");
        String link = myService.difBetweenYesterdayAndTodayRate(currency);
        log.info("Get response with gif");
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(link)).build();
    }

    @GetMapping(value = "/check/{currency}/today", produces = "application/json")
    public RateDto getJsonRateToday(@PathVariable String currency) {
        log.info("Get request /check/{currency}/today");
        return myService.getRateUSDbyCurrencyToday(currency).getRate();
    }

    @GetMapping(value = "/check/{currency}/yesterday", produces = "application/json")
    public RateDto getJsonRateYesterday(@PathVariable String currency) {
        log.info("Get request /check/{currency}/yesterday");
        return myService.getRateUSDbyCurrencyYesterday(currency);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    class InvalidRequestException extends RuntimeException {
        public InvalidRequestException(String s) {
            super(s);
        }
    }
}