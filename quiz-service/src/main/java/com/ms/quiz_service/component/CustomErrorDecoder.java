package com.ms.quiz_service.component;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 500) {
            return new Exception("Internal server error while calling question-service");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
