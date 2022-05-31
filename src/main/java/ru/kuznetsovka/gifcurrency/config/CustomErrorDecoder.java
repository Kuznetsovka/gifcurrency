package ru.kuznetsovka.gifcurrency.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;
import ru.kuznetsovka.gifcurrency.exception.BusinessException;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BusinessException("Error 400");
            case 404:
                return new BusinessException("Not found exception Error 404");
            default:
                return new BusinessException("Some error");
        }
    }
}