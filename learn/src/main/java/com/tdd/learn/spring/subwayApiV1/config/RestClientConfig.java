package com.tdd.learn.spring.subwayApiV1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder().baseUrl("http://openapi.seoul.go.kr:8088").build();
    }
}

