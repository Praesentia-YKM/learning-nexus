package com.learn.spring.poi.excel.format.download.domain;

public class ExcelFormulaGenerator {

    /**
     * VLOOKUP 수식 생성
     * @param colName   검색할 셀의 컬럼명 (예: "A")
     * @param sheetName 참조할 시트명
     * @param rangeSize 데이터 범위 크기
     * @return VLOOKUP 엑셀 수식 문자열
     */
    public static String generateVLookupFormula(String colName, String sheetName, int rangeSize) {
        return String.format("IFERROR(VLOOKUP(SUBSTITUTE(%s2,\"~\", \"~~\"), %s!$A$1:$B$%d, 2, FALSE), \"\")",
                colName, sheetName, rangeSize + 1);
    }

    /**
     * COUNTIF 수식 생성 (자동 넘버링)
     * @param colName   번호를 매길 컬럼명 (예: "A")
     * @return COUNTIF 엑셀 수식 문자열
     */
    public static String generateCountIfFormula(String colName) {
        return String.format("COUNTIF($%s$2:%s2, %s2)", colName, colName, colName);
    }
}
