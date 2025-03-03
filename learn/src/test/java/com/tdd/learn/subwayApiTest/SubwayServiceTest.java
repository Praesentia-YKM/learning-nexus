package com.tdd.learn.subwayApiTest;

import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassenger;
import com.tdd.learn.spring.subwayApiV1.respository.SubwayPassengerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubwayServiceTest {

    private SubwayPassengerRepository repository;
    private SubwayService subwayService;

    @BeforeEach
    public void setUp() {
        repository = mock(SubwayPassengerRepository.class);
        subwayService = new SubwayService(repository);
    }

//    @DisplayName("조회 버튼을 누르면 특정 호선과 역의 시간대별 승하차 인원 조회가 가능하다.")
//    @Test
//    public void testGetPassengerData_Success() throws ParseException {
//        // given
//        String line = "2호선";
//        String station = "강남";
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sd.parse("2025-03-02");
//        String formattedDate = sd.format(date);
//
//        // when
//        List<SubwayPassenger> mockData = List.of(
//            new SubwayPassenger(1L, line, station, formattedDate, "08:00", 1200, 800),
//            new SubwayPassenger(2L, line, station, formattedDate, "09:00", 1500, 900)
//        );
//
//        // 리포즈토리에 대한 Stubbing
//        when(
//            repository.findByLineAndStationAndDate(line, station, formattedDate)
//        ).thenReturn(mockData);
//
//        List<SubwayPassenger> result = subwayService.getPassengerData(line, station, formattedDate);
//
//        // then
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals(1200, result.get(0).getInPassengers());
//        assertEquals(1500, result.get(1).getInPassengers());
//    }
//
//    @DisplayName("없는 역을 검색하면 에러를 던진다.")
//    @Test
//    void testGetPassengerData_NoData() {
//        // given
//        String line = "2호선";
//        String station = "없는역";
//        String date = "2025-03";
//
//        when(
//            repository.findByLineAndStationAndDate(line, station, date)
//        ).thenReturn(List.of());
//
//        // when & then
//        assertThrows(
//            RuntimeException.class,
//            () -> subwayService.getPassengerData(line, station, date)
//        );
//    }
}
