package ru.kuznetsovka.gifcurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/api/v1/")
public class GifController {
    @Autowired
    private MyService myService;

    @GetMapping(value = "/{currency}")
    public ResponseEntity<Void> getGif(@PathVariable String currency) {
        String link = myService.difBetweenYesterdayAndTodayRate(currency);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(link)).build();
    }

}