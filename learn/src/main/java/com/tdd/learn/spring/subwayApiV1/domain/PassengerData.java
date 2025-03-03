package com.tdd.learn.spring.subwayApiV1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tdd.learn.spring.subwayApiV1.api.CardSubwayTimeDTO;
import lombok.Data;


@Data
public class PassengerData {
    @JsonProperty("CardSubwayTime")
    private CardSubwayTimeDTO cardSubwayTime;
}

