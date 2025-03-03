package com.tdd.learn.spring.subwayApiV1.presentation;

import com.tdd.learn.spring.subwayApiV1.api.SubwayApiClient;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;
import com.tdd.learn.spring.subwayApiV1.service.SubwayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/api/subway")
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayApiClient subwayApiClient;
    private final SubwayService subwayService;
    private final RestClient restClient;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/show")
    public String show() {
        return "subway";
    }

    @GetMapping("/fetch")
    public ResponseEntity<PassengerData> fetchSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {
        PassengerData passengerData = subwayApiClient.fetchPassengerData(line, station, date);
        return ResponseEntity.ok(passengerData);
    }

    @GetMapping("/search")
    public ResponseEntity<SubwayPassengerEntity> searchSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {
        Optional<SubwayPassengerEntity> result = subwayService.getPassengerData(line, station, date);
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePassengerData(@RequestBody PassengerData passengerData) {
        subwayService.saveOrUpdatePassengerRecords(passengerData);
        return ResponseEntity.ok("지하철 데이터 저장 성공!");
    }

    //public ResponseEntity<String> searchAndSaveSubwayData(
    @PostMapping("/fetch-and-save")
    public ModelAndView searchAndSaveSubwayData(
            @RequestParam String line,
            @RequestParam String station,
            @RequestParam String date) {

        ModelAndView mav = new ModelAndView("subway");

        PassengerData passengerData = subwayApiClient.fetchPassengerData(line, station, date);

        if (passengerData == null || passengerData.getCardSubwayTime() == null) {
            //return ResponseEntity.badRequest().body("데이터를 가져오지 못했습니다.");
            mav.addObject("message","조회할 데이터가 존재하지 않습니다.");
        }

        subwayService.saveOrUpdatePassengerRecords(passengerData);

        mav.addObject("passengerData", passengerData);
        mav.addObject("message", "지하철 데이터 조회 및 저장 성공!");
        return mav;
        //return ResponseEntity.ok("지하철 데이터 조회 및 저장 성공!");
    }

}
