package com.tdd.learn.subwayApiTest;

import com.tdd.learn.spring.subwayApiV1.api.CardSubwayTimeDTO;
import com.tdd.learn.spring.subwayApiV1.api.ResultDTO;
import com.tdd.learn.spring.subwayApiV1.api.SubwayRecordDTO;
import com.tdd.learn.spring.subwayApiV1.domain.PassengerData;
import com.tdd.learn.spring.subwayApiV1.entity.SubwayPassengerEntity;
import com.tdd.learn.spring.subwayApiV1.mapper.PassengerDataMapper;
import com.tdd.learn.spring.subwayApiV1.respository.SubwayPassengerRepository;
import com.tdd.learn.spring.subwayApiV1.service.SubwayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubwayServiceTest {

    @Mock
    private SubwayPassengerRepository repository;  // Mock Repository

    private SubwayService subwayService;

    private PassengerData mockPassengerData;

    @BeforeEach
    void setUp() {
        // Mock Repository 주입
        subwayService = new SubwayService(repository);

        // 테스트 API 응답 데이터 생성
        SubwayRecordDTO record = new SubwayRecordDTO();
        record.setSubwayLineName("2호선");
        record.setStation("강남");
        record.setUseMonth("202501");

        Map<String, Integer> testPassengerData = new HashMap<>();
        testPassengerData.put("HR_10_GET_ON_NOPE", 5000);
        testPassengerData.put("HR_10_GET_OFF_NOPE", 4500);
        record.setPassengerData(testPassengerData);

        List<SubwayRecordDTO> records = List.of(record);
        mockPassengerData = new PassengerData();
        mockPassengerData.setCardSubwayTime(
            new CardSubwayTimeDTO(
                1,
                new ResultDTO("INFO-000", "정상 처리되었습니다"),
                records)
        );
    }

    // todo 실제 비지니스 service를 가지고 테스트해도 될까? TDD 시점에는 이 서비스 코드가 구체화 되어있지 않을텐데 어떻게 했을까?
    // todo 이래서 InjectMocks 가 생긴건가??
    @DisplayName("API응답에 대한 저장 테스트는 해당 메서드가 호출되었는지로 확인한다.")
    @Test
    void newData_ShouldSave() {
        // given: Repository가 기존 데이터를 반환하지 않도록 설정
        when(
            repository.findByLineAndStationAndDate(anyString(), anyString(), anyString())
        ).thenReturn(Optional.empty());

        // when: 새로운 데이터 저장 요청
        subwayService.saveOrUpdatePassengerRecords(mockPassengerData);

        // then: repository.saveAll()이 한 번 호출되었는지 확인
        verify(repository, times(1)).saveAll(anyList());
    }

    @DisplayName("api 호출 시, 이미 저장된 데이터면 insert가 아닌 update를 진행한다. (dirty check)")
    @Test
    void existingData_ShouldUpdate() {
        // given: 기존 데이터가 존재하는 경우
        SubwayRecordDTO firstRecord = mockPassengerData.getCardSubwayTime().getRow().getFirst();
        SubwayPassengerEntity existingEntity = PassengerDataMapper.toEntity(firstRecord);

        // 기존 데이터가 존재한다고 가정
        when(repository.findByLineAndStationAndDate(
            anyString(),
            anyString(),
            anyString())
        ).thenReturn(Optional.of(existingEntity));

        // 기존 데이터 개수
        long beforeCount = repository.count();

        // when: 데이터 저장 요청
        subwayService.saveOrUpdatePassengerRecords(mockPassengerData);

        // then: 기존 데이터가 업데이트되므로 insert가 발생하지 않음
        long afterCount = repository.count();
        assertEquals(beforeCount, afterCount, "기존 데이터만 수정되므로 행 개수 변화가 없어야 함");
    }

    @DisplayName("빈값에 대한 널체크 테스트")
    @Test
    void noData_ShouldThrowException() {
        // given: 비어있는 데이터
        PassengerData emptyData = new PassengerData();
        emptyData.setCardSubwayTime(new CardSubwayTimeDTO(0, new ResultDTO("INFO-000", "정상 처리되었습니다"), new ArrayList<>()));

        // when & then: 예외 발생 검증
        IllegalArgumentException exception =
            assertThrows(
                IllegalArgumentException.class,
                () -> subwayService.saveOrUpdatePassengerRecords(emptyData)
            );

        assertEquals("저장할 데이터가 없습니다.", exception.getMessage());
    }
}
