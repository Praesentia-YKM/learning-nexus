package com.learn.spring.poi.excel.format.download.domain.provider;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 엑셀 시트 데이터 제공자 인터페이스
 */
@Component
public interface SheetDataProvider {
    List<Map<String, Object>> fetchData(String formCd, Map<String, String> params);
}