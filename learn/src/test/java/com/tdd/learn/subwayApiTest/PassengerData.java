package com.tdd.learn.subwayApiTest;

import java.util.Map;

public class PassengerData {
    private String stationName;   // 역 이름
    private String date;          // 조회 날짜 (yyyy-MM-dd)
    private Map<String, Integer> hourlyData; // 시간대별 승하차 인원 (예: "08:00": 1200)

    public PassengerData() {}

    public PassengerData(String stationName, String date, Map<String, Integer> hourlyData) {
        this.stationName = stationName;
        this.date = date;
        this.hourlyData = hourlyData;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Integer> getHourlyData() {
        return hourlyData;
    }

    public void setHourlyData(Map<String, Integer> hourlyData) {
        this.hourlyData = hourlyData;
    }

    @Override
    public String toString() {
        return "PassengerData{" +
                "stationName='" + stationName + '\'' +
                ", date='" + date + '\'' +
                ", hourlyData=" + hourlyData +
                '}';
    }
}

