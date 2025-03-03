package com.tdd.learn.spring.subwayApiV1.api;

import com.tdd.learn.spring.subwayApiV1.config.ApiProperties;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class SubwayApiClient {
    private final RestClient restClient;
    private final ApiProperties apiProperties;

    public SubwayApiClient(RestClient restClient, ApiProperties apiProperties) {
        this.restClient = restClient;
        this.apiProperties = apiProperties;
    }

    /**
     * 특정 지하철역의 승하차 인원 데이터를 동기적으로 조회하는 메서드
     * http://openapi.seoul.go.kr:8088/{인증키}/json/CardSubwayTime/1/5/{날짜}/{호선}/{역명}/
     */
    public PassengerData fetchPassengerData(String line, String station, String date) {
        String apiKey = apiProperties.getApiKey();
        String formattedDate = date.replace("-", "");
        String uri = String.format("/%s/json/CardSubwayTime/1/5/%s/%s/%s", apiKey, formattedDate, line, station);

        try {
            PassengerData response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(PassengerData.class);

            System.out.println("응답 데이터: " + response);
            return response;
        } catch (RestClientException e) {
            System.err.println("API 호출 실패: " + e.getMessage());
            return null;
        }
    }
}
