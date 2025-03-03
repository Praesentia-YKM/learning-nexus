package com.tdd.learn.spring.subwayApiV1.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardSubwayTimeDTO {
    @JsonProperty("list_total_count")
    private int listTotalCount;

    @JsonProperty("RESULT")
    private ResultDTO result;

    @JsonProperty("row")
    private List<SubwayRecordDTO> row;
}
