package com.tdd.learn.spring.subwayApiV1.mapper;

import com.tdd.learn.spring.subwayApiV1.api.SubwayRecordDTO;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PassengerDataMapper {

    public static SubwayPassengerEntity toEntity(SubwayRecordDTO dto) {
        return new SubwayPassengerEntity(
            null,  // ID는 자동 생성 (DB에서 관리)
            dto.getSubwayLineName(),
            dto.getStation(),
            dto.getUseMonth(),
            dto.getPassengerData() // 시간별 승하차 인원 데이터 저장
        );
    }

    public static List<SubwayPassengerEntity> toEntityList(PassengerData passengerData) {
        return passengerData.getCardSubwayTime().getRow().stream()
            .map(row -> {
                // DTO 생성
                SubwayRecordDTO dto = new SubwayRecordDTO();
                dto.setUseMonth(row.getUseMonth());
                dto.setSubwayLineName(row.getSubwayLineName());
                dto.setStation(row.getStation());

                // 시간별 데이터 추가
                for (int hour = 0; hour < 24; hour++) {
                    String hourKey = String.format("HR_%d", hour);
                    dto.setHourData(hourKey + "_GET_ON_NOPE", row.getInPassengers(hour));
                    dto.setHourData(hourKey + "_GET_OFF_NOPE", row.getOutPassengers(hour));
                }

                // DTO -> 개별 엔티티 변환 후 반환
                return toEntity(dto);
            })
            .collect(Collectors.toList());
    }

}
