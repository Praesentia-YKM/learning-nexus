package com.tdd.learn.spring.subwayApiV1.presentation;

import com.tdd.learn.spring.subwayApiV1.api.SubwayApiClient;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;
import com.tdd.learn.spring.subwayApiV1.service.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/subway")
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayApiClient subwayApiClient;
    private final SubwayService subwayService;

    @GetMapping("/fetch")
    public Mono<PassengerData> fetchSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {
        return subwayApiClient.fetchPassengerData(line, station, date);
    }

    @GetMapping("/search")
    public Optional<SubwayPassengerEntity> searchSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {
        return subwayService.getPassengerData(line, station, date);
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePassengerData(@RequestBody PassengerData dtoList) {
        subwayService.saveOrUpdatePassengerRecords(dtoList);
        return ResponseEntity.ok("지하철 데이터 저장 성공!");
    }

    @PostMapping("/fetch-and-save")
    public Mono<ResponseEntity<String>> searchAndSaveSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {

        return subwayApiClient
            .fetchPassengerData(line, station, date)
            .doOnNext(subwayService::saveOrUpdatePassengerRecords) // DB 저장
            .map(
                dtoList -> ResponseEntity.ok("지하철 데이터 조회 및 저장 성공!")
            )
            .defaultIfEmpty(
                ResponseEntity.badRequest().body("데이터를 가져오지 못했습니다.")
            );
    }
}

