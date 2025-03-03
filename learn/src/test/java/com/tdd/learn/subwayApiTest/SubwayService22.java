package com.tdd.learn.subwayApiTest;

import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;
import com.tdd.learn.spring.subwayApiV1.respository.SubwayPassengerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubwayService22 {

    private final SubwayPassengerRepository repository;

    public SubwayService22(SubwayPassengerRepository repository) {
        this.repository = repository;
    }

    public Optional<SubwayPassengerEntity> getPassengerData(String line, String station, String date) {
        Optional<SubwayPassengerEntity> passengers = repository.findByLineAndStationAndDate(line, station, date);

        if (passengers.isEmpty()) {
            throw new RuntimeException("해당 데이터가 존재하지 않습니다.");
        }

        return passengers;
    }
}
