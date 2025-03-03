CREATE TABLE IF NOT EXISTS subway_passenger (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    line VARCHAR(255) NOT NULL,    -- 지하철 호선
    station VARCHAR(255) NOT NULL, -- 역 이름
    date VARCHAR(10) NOT NULL      -- YYYY-MM 형식의 날짜
);

CREATE TABLE IF NOT EXISTS passenger_time_data (
    passenger_id BIGINT NOT NULL,         -- subway_passenger 테이블의 FK
    time_slot VARCHAR(50) NOT NULL,       -- 시간대 키 (예: HR_4_GET_ON_NOPE)
    passenger_count INT NOT NULL,         -- 해당 시간의 승하차 인원 수
    PRIMARY KEY (passenger_id, time_slot), -- 🚀 복합키 설정 (1명의 승객당 여러 시간대 기록 가능)
    FOREIGN KEY (passenger_id) REFERENCES subway_passenger(id) ON DELETE CASCADE
);
