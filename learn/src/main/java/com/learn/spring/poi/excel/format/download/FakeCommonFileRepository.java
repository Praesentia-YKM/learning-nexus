package com.learn.spring.poi.excel.format.download;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FakeCommonFileRepository implements CommonFileDAO {
    private final List<Map<String, String>> fakeData;

    public FakeCommonFileRepository() {
        this.fakeData = new ArrayList<>();

        // 가짜 데이터 삽입
        Map<String, String> format1 = new HashMap<>();
        format1.put("FORMAT_CD", "FORMAT_001");
        format1.put("HEADER", "이름");
        format1.put("COL_USE_YN", "Y");
        format1.put("COL_WIDTH", "3000");
        format1.put("REUIRE_YN", "N");
        format1.put("FIRST_ROW_DATA", "홍길동");

        Map<String, String> format2 = new HashMap<>();
        format2.put("FORMAT_CD", "FORMAT_002");
        format2.put("HEADER", "나이");
        format2.put("COL_USE_YN", "Y");
        format2.put("COL_WIDTH", "2000");
        format2.put("REUIRE_YN", "Y");
        format2.put("FIRST_ROW_DATA", "25");

        fakeData.add(format1);
        fakeData.add(format2);
    }

    @Override
    public List<Map<String, String>> selectAllExcelForms() {
        return new ArrayList<>(fakeData); // 데이터 복사하여 반환 (원본 보호)
    }
}
