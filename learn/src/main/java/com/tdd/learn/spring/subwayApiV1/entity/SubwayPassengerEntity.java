package com.tdd.learn.spring.subwayApiV1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subway_passenger")
public class SubwayPassengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line;  // 지하철 호선
    private String station;  // 역 이름
    private String date;  // yyyy-MM
//    private String hour1;  // HH:mm
//
//    private int inPassengers;  // 승차 인원
//    private int outPassengers; // 하차 인원
    @ElementCollection
    @CollectionTable(name = "passenger_time_data", joinColumns = @JoinColumn(name = "passenger_id"))
    @MapKeyColumn(name = "time_slot")
    @Column(name = "passenger_count")
    private Map<String, Integer> passengerData;
}

