package com.tdd.learn.subwayApiTest;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subway_passenger_data")
public class SubwayPassenger22 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line;  // 지하철 호선
    private String station;  // 역 이름
    private String date;  // yyyy-MM-dd
    private String hour;  // HH:mm (예: 08:00)

    private int inPassengers;  // 승차 인원
    private int outPassengers; // 하차 인원
}

