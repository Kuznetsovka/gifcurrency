package ru.kuznetsovka.gifcurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
//@EnableSwagger2
@ComponentScan(basePackageClasses = GifController.class)
public class GifCurrencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(GifCurrencyApplication.class, args);
    }

}
