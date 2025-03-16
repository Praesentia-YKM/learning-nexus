package com.learn.spring.poi.excel.format.download.domain;

import com.learn.spring.poi.excel.format.download.CommonFileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ExcelFormatLoader {

    private final CommonFileDAO commonFileDAO;
    private final Map<String, ExcelFormatData> excelFormatMap = new HashMap<>();

    @Autowired
    public ExcelFormatLoader(CommonFileDAO commonFileDAO) {
        if (commonFileDAO == null) {
            throw new IllegalStateException("CommonFileDAO가 주입되지 않았습니다.");
        }
        this.commonFileDAO = commonFileDAO;
    }

    /**
     * 엑셀 포맷 데이터를 로드하여 캐싱
     */
    public void loadExcelFormats() throws Exception {
        List<Map<String, String>> formatList = commonFileDAO.selectAllExcelForms();
        for (Map<String, String> format : formatList) {
            String formatCd = Optional.ofNullable(format.get("FORMAT_CD"))
                .orElseThrow(() -> new Exception("공통코드가 설정되어있지 않습니다.\n관리자에게 문의해주세요."));

            ExcelFormatData data = new ExcelFormatData(
                formatCd,
                format.getOrDefault("HEADER", ""),
                format.getOrDefault("FORM_CD", ""),
                format.getOrDefault("COL_USE_YN", ""),
                format.getOrDefault("COL_WIDTH", ""),
                format.getOrDefault("REUIRE_YN", ""),
                format.getOrDefault("FIRST_ROW_DATA", "")
            );
            excelFormatMap.put(formatCd, data);
        }
    }

    /**
     * 특정 formatCd에 대한 엑셀 포맷 정보 조회
     */
    public ExcelFormatData getExcelFormat(String formatCd) {
        return excelFormatMap.get(formatCd);
    }

    /**
     * 캐싱된 모든 포맷 데이터 반환
     */
    public Map<String, ExcelFormatData> getAllFormats() {
        return Collections.unmodifiableMap(excelFormatMap);
    }

    /**
     * 포맷을 다시 로드 (Refresh)
     */
    public void reloadFormats() throws Exception {
        excelFormatMap.clear();
        loadExcelFormats();
    }
}
