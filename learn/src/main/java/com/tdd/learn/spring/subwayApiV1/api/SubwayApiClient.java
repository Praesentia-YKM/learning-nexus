package com.tdd.learn.spring.subwayApiV1.api;

import com.tdd.learn.spring.subwayApiV1.config.ApiProperties;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SubwayApiClient {

    private final WebClient webClient;
    private final ApiProperties apiProperties;  // API 키 불러오기

    /**
     * 특정 지하철역의 승하차 인원 데이터를 조회하는 메서드
     * http://openapi.seoul.go.kr:8088/{인증키}/xml/CardSubwayTime/1/5/{날짜}/{호선}/{역명}/
     */
    public Mono<PassengerData> getPassengerData(String line, String station, String date) {
        String apiKey = apiProperties.getApiKey();
        return webClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .pathSegment(apiKey, "json", "CardSubwayTime", "1", "5", date, line, station)
                .build()
            )
            .retrieve()
            .bodyToMono(PassengerData.class)
            .onErrorResume(e -> {
                System.err.println("API 호출 실패: " + e.getMessage());
                return Mono.empty();
            });
    }
}
