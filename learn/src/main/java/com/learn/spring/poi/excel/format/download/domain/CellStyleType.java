package com.learn.spring.poi.excel.format.download.domain;

import lombok.Getter;
import org.apache.poi.ss.usermodel.IndexedColors;

@Getter
public enum CellStyleType {
    HEADER("헤더 스타일 (강조) - 연두색 배경", IndexedColors.LIME),
    NORMAL("일반 텍스트 스타일 - 기본값 (배경 없음)", IndexedColors.WHITE),
    CODE("코드 값 스타일 - 회색 배경, 잠금 처리", IndexedColors.GREY_25_PERCENT),
    CODE_NAME("코드명 스타일 - 연한 노랑 배경", IndexedColors.LEMON_CHIFFON),
    DATE("날짜 스타일 - yyyy-MM-dd 형식", IndexedColors.WHITE),
    REQUIRED("필수 입력 필드 스타일 - 갈색 배경", IndexedColors.TAN);

    private final String description;
    private final IndexedColors color;

    CellStyleType(String description, IndexedColors color) {
        this.description = description;
        this.color = color;
    }

}

