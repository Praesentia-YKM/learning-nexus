package com.tdd.learn.spring.subwayApiV1.service;

import com.tdd.learn.spring.subwayApiV1.respository.SubwayPassengerRepository;
import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubwayService {

    private final SubwayPassengerRepository repository;

    public SubwayService(SubwayPassengerRepository repository) {
        this.repository = repository;
    }

    public List<SubwayPassenger> getPassengerData(String line, String station, String date) {
        List<SubwayPassenger> passengers = repository.findByLineAndStationAndDate(line, station, date);

        if (passengers.isEmpty()) {
            throw new RuntimeException("해당 데이터가 존재하지 않습니다.");
        }

        return passengers;
    }
}
