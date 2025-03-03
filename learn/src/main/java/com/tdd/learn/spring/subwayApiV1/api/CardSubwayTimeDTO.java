package com.tdd.learn.spring.subwayApiV1.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CardSubwayTimeDTO {
    @JsonProperty("list_total_count")
    private int listTotalCount;

    @JsonProperty("RESULT")
    private ResultDTO result;

    @JsonProperty("row")
    private List<SubwayRecordDTO> row;
}
