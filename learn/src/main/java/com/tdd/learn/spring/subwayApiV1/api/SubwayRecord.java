package com.tdd.learn.spring.subwayApiV1.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class SubwayRecord {
    @JsonProperty("USE_MM")
    private String useMonth; // 사용 월

    @JsonProperty("SBWY_ROUT_LN_NM")
    private String subwayLineName; // 지하철 노선

    @JsonProperty("STTN")
    private String station; // 역 이름

    @JsonProperty("JOB_YMD")
    private String jobYmd; // 작업 날짜

    // 시간별 승차 인원
    @JsonProperty("HR_4_GET_ON_NOPE")
    private int hr4GetOn;

    @JsonProperty("HR_5_GET_ON_NOPE")
    private int hr5GetOn;

    @JsonProperty("HR_6_GET_ON_NOPE")
    private int hr6GetOn;

    @JsonProperty("HR_7_GET_ON_NOPE")
    private int hr7GetOn;

    @JsonProperty("HR_8_GET_ON_NOPE")
    private int hr8GetOn;

    @JsonProperty("HR_9_GET_ON_NOPE")
    private int hr9GetOn;

    @JsonProperty("HR_10_GET_ON_NOPE")
    private int hr10GetOn;

    @JsonProperty("HR_11_GET_ON_NOPE")
    private int hr11GetOn;

    @JsonProperty("HR_12_GET_ON_NOPE")
    private int hr12GetOn;

    @JsonProperty("HR_13_GET_ON_NOPE")
    private int hr13GetOn;

    @JsonProperty("HR_14_GET_ON_NOPE")
    private int hr14GetOn;

    @JsonProperty("HR_15_GET_ON_NOPE")
    private int hr15GetOn;

    @JsonProperty("HR_16_GET_ON_NOPE")
    private int hr16GetOn;

    @JsonProperty("HR_17_GET_ON_NOPE")
    private int hr17GetOn;

    @JsonProperty("HR_18_GET_ON_NOPE")
    private int hr18GetOn;

    @JsonProperty("HR_19_GET_ON_NOPE")
    private int hr19GetOn;

    @JsonProperty("HR_20_GET_ON_NOPE")
    private int hr20GetOn;

    @JsonProperty("HR_21_GET_ON_NOPE")
    private int hr21GetOn;

    @JsonProperty("HR_22_GET_ON_NOPE")
    private int hr22GetOn;

    @JsonProperty("HR_23_GET_ON_NOPE")
    private int hr23GetOn;

    @JsonProperty("HR_0_GET_ON_NOPE")
    private int hr0GetOn;

    @JsonProperty("HR_1_GET_ON_NOPE")
    private int hr1GetOn;

    @JsonProperty("HR_2_GET_ON_NOPE")
    private int hr2GetOn;

    @JsonProperty("HR_3_GET_ON_NOPE")
    private int hr3GetOn;

    // 시간별 하차 인원
    @JsonProperty("HR_4_GET_OFF_NOPE")
    private int hr4GetOff;

    @JsonProperty("HR_5_GET_OFF_NOPE")
    private int hr5GetOff;

    @JsonProperty("HR_6_GET_OFF_NOPE")
    private int hr6GetOff;

    @JsonProperty("HR_7_GET_OFF_NOPE")
    private int hr7GetOff;

    @JsonProperty("HR_8_GET_OFF_NOPE")
    private int hr8GetOff;

    @JsonProperty("HR_9_GET_OFF_NOPE")
    private int hr9GetOff;

    @JsonProperty("HR_10_GET_OFF_NOPE")
    private int hr10GetOff;

    @JsonProperty("HR_11_GET_OFF_NOPE")
    private int hr11GetOff;

    @JsonProperty("HR_12_GET_OFF_NOPE")
    private int hr12GetOff;

    @JsonProperty("HR_13_GET_OFF_NOPE")
    private int hr13GetOff;

    @JsonProperty("HR_14_GET_OFF_NOPE")
    private int hr14GetOff;

    @JsonProperty("HR_15_GET_OFF_NOPE")
    private int hr15GetOff;

    @JsonProperty("HR_16_GET_OFF_NOPE")
    private int hr16GetOff;

    @JsonProperty("HR_17_GET_OFF_NOPE")
    private int hr17GetOff;

    @JsonProperty("HR_18_GET_OFF_NOPE")
    private int hr18GetOff;

    @JsonProperty("HR_19_GET_OFF_NOPE")
    private int hr19GetOff;

    @JsonProperty("HR_20_GET_OFF_NOPE")
    private int hr20GetOff;

    @JsonProperty("HR_21_GET_OFF_NOPE")
    private int hr21GetOff;

    @JsonProperty("HR_22_GET_OFF_NOPE")
    private int hr22GetOff;

    @JsonProperty("HR_23_GET_OFF_NOPE")
    private int hr23GetOff;

    @JsonProperty("HR_0_GET_OFF_NOPE")
    private int hr0GetOff;

    @JsonProperty("HR_1_GET_OFF_NOPE")
    private int hr1GetOff;

    @JsonProperty("HR_2_GET_OFF_NOPE")
    private int hr2GetOff;

    @JsonProperty("HR_3_GET_OFF_NOPE")
    private int hr3GetOff;
}
