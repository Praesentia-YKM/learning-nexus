package com.learn.spring.poi.excel.format.download.domain.provider;

import com.learn.spring.poi.excel.format.download.FakeCommonFileRepository;

import java.util.List;
import java.util.Map;

/**
 * 공통 코드 테이블에서 데이터 조회
 */
public class CommonCodeSheetDataProvider implements SheetDataProvider {
    private FakeCommonFileRepository commonFileDAO;

    @Override
    public List<Map<String, Object>> fetchData(String formCd, Map<String, String> params) {
        //return commonFileDAO.selectComCd(formCd);
        return null;
    }
}