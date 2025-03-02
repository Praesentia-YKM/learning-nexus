CREATE TABLE subway_passenger (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    line VARCHAR(50),
    station VARCHAR(50),
    date VARCHAR(10),
    `hour` VARCHAR(5), -- hour가 예약어라서 백틱(일부 db는 대괄호)으로 감쌈
    boarding_passengers INT,
    alighting_passengers INT
);
