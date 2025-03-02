package com.tdd.learn.spring.subwayApiV1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Spring의 @Value는 환경 변수 또는 application.yml, application.properties의 값을 주입하는 역할
 */
@Component
public class ApiProperties {

    @Value("${seoul.api.key}") // Lombok의 value가 아님
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}

