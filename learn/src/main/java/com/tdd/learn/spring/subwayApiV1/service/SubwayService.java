package com.tdd.learn.spring.subwayApiV1.service;

import com.tdd.learn.spring.subwayApiV1.api.SubwayRecordDTO;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;
import com.tdd.learn.spring.subwayApiV1.mapper.PassengerDataMapper;
import com.tdd.learn.spring.subwayApiV1.respository.SubwayPassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubwayService {

    private final SubwayPassengerRepository repository;

    public SubwayService(SubwayPassengerRepository repository) {
        this.repository = repository;
    }

    public Optional<SubwayPassengerEntity> getPassengerData(String line, String station, String date) {
        Optional<SubwayPassengerEntity> passenger = repository.findByLineAndStationAndDate(line, station, date);

        if (passenger.isEmpty()) {
            throw new RuntimeException("해당 데이터가 존재하지 않습니다.");
        }

        return passenger;
    }

    @Transactional
    public void saveOrUpdatePassengerRecords(PassengerData apiData) {
        List<SubwayRecordDTO> dtoList = apiData.getCardSubwayTime().getRow();
        if (dtoList == null || dtoList.isEmpty()) {
            throw new IllegalArgumentException("저장할 데이터가 없습니다.");
        }
    
        List<SubwayPassengerEntity> newPassengers = new ArrayList<>();
    
        for (SubwayRecordDTO dto : dtoList) {
            // DB에서 기존 데이터 조회
            Optional<SubwayPassengerEntity> existingRecord = repository.findByLineAndStationAndDate(
                dto.getSubwayLineName(), dto.getStation(), dto.getUseMonth()
            );
    
            if (existingRecord.isPresent()) {
                // 기존 데이터가 있으면 업데이트
                SubwayPassengerEntity passenger = existingRecord.get();
    
                Map<String, Integer> newPassengerData = dto.getPassengerData();
                if (newPassengerData == null) {
                    newPassengerData = new HashMap<>();
                }

                // 기존 데이터를 수정 (findBy로 인해 엔티티가 영속 상태가 되어 JPA 변경 감지 활성화)
                passenger.getPassengerData().clear();
                passenger.getPassengerData().putAll(newPassengerData);
    
            } else {
                // 새로운 데이터는 리스트에 추가
                newPassengers.add(PassengerDataMapper.toEntity(dto));
            }
        }
    
        // 신규 데이터만 한 번에 저장
        if (!newPassengers.isEmpty()) {
            repository.saveAll(newPassengers);
        }
    }
    
}
