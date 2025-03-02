package com.tdd.learn.spring.subwayApiV1.presentation;

import com.tdd.learn.spring.subwayApiV1.api.SubwayApiClient;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayApiClient subwayApiClient;

    @GetMapping("/subway")
    public Mono<PassengerData> getSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {
        return subwayApiClient.getPassengerData(line, station, date);
    }
}

