package com.tdd.learn.spring.subwayApiV1.respository;

import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// save, findById, findAll, delete를 자동으로 제공
@Repository
public interface SubwayPassengerRepository extends JpaRepository<SubwayPassengerEntity, Long> {

    // 특정 호선, 역, 날짜에 해당하는 데이터 조회
    Optional<SubwayPassengerEntity> findByLineAndStationAndDate(String line, String station, String date);
}
