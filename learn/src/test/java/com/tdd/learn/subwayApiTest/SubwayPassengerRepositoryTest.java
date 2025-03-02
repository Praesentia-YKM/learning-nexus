package com.tdd.learn.subwayApiTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest // JPA 테스트 전용 애너테이션 (H2 DB 자동 설정)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubwayPassengerRepositoryTest {
//
//    @Autowired
//    private SubwayPassengerRepository repository;
//
//    @Test
//    @DisplayName("지하철 승객 데이터를 저장하고 조회할 수 있다.")
//    void testSaveAndFind() {
//        // given
//        SubwayPassenger passenger = new SubwayPassenger(
//            null, "2호선", "강남", "2025-03", "08:00", 1200, 800
//        );
//
//        // whem
//        SubwayPassenger saved = repository.save(passenger);
//        Optional<SubwayPassenger> found = repository.findById(saved.getId());
//
//        // then
//        assertTrue(found.isPresent());
//        assertEquals("강남", found.get().getStation());
//        assertEquals(1200, found.get().getInPassengers());
//    }
//
//    @Test
//    @DisplayName("특정 호선과 역의 데이터를 조회할 수 있다.")
//    void testFindByLineAndStationAndDate() {
//        // given
//        SubwayPassenger passenger = new SubwayPassenger(
//            null, "2호선", "강남", "2025-03", "08:00", 1300, 900
//        );
//        repository.save(passenger);
//
//        // when
//        List<SubwayPassenger> foundPassengers = repository.findByLineAndStationAndDate("2호선", "강남", "2025-03");
//
//        // then
//        assertFalse(foundPassengers.isEmpty());
//        assertEquals(1, foundPassengers.size());
//        assertEquals(1300, foundPassengers.get(0).getInPassengers());
//    }
//
//    @Test
//    @DisplayName("🚀 데이터를 수정할 수 있다.")
//    void testUpdatePassenger() {
//        // given
//        SubwayPassenger passenger = new SubwayPassenger(
//            null, "2호선", "강남", "2025-03", "08:00", 1100, 700
//        );
//        SubwayPassenger saved = repository.save(passenger);
//
//        // when
//        saved.setInPassengers(2000);
//        SubwayPassenger updated = repository.save(saved);
//
//        // then
//        assertEquals(2000, updated.getInPassengers());
//    }
//
//    @Test
//    @DisplayName("데이터를 삭제할 수 있다.")
//    void testDeletePassenger() {
//        // given
//        SubwayPassenger passenger = new SubwayPassenger(
//            null, "2호선", "강남", "2025-03", "08:00", 900, 600
//        );
//        SubwayPassenger saved = repository.save(passenger);
//
//        // when
//        repository.deleteById(saved.getId());
//        Optional<SubwayPassenger> found = repository.findById(saved.getId());
//
//        // then
//        assertFalse(found.isPresent());
//    }
}
