package com.learn.spring.poi.excel.format.download.domain;

/**
 * 엑셀 양식 메타 데이터
 */
import lombok.Getter;
import java.util.*;

@Getter
public class ExcelFormatData {
    private final String formatCd;
    private final List<String> headers;
    private final List<String> formCds;
    private final List<String> colUseYn;
    private final List<Integer> colWidths;
    private final List<String> requireYn;
    private final List<String> firstRowCells;
    private final LinkedHashMap<String, String> sheetNmByHeaderMap;

    public ExcelFormatData(
        String formatCd,
        String headerStr,
        String formCdStr,
        String colUseYnStr,
        String colWidthStr,
        String requireYnStr,
        String firstRowData) {

        this.formatCd = formatCd;
        this.headers = parseList(headerStr);
        this.formCds = parseList(formCdStr);
        this.colUseYn = parseList(colUseYnStr);
        this.requireYn = parseList(requireYnStr);
        this.colWidths = parseColWidths(colWidthStr);
        this.firstRowCells = parseList(firstRowData);

        this.sheetNmByHeaderMap = new LinkedHashMap<>();
        this.sheetNmByHeaderMap.put("default", "양식"); // 첫번째 시트
        for (int i = 0; i < Math.min(this.formCds.size(), this.headers.size()); i++) {
            String formCd = this.formCds.get(i).trim();
            if (!formCd.isEmpty()) {
                this.sheetNmByHeaderMap.put(formCd, this.headers.get(i));
            }
        }
    }

    public String findSheetNmByHeaderMap(String formCd) {
        return sheetNmByHeaderMap.get(formCd);
    }

    private List<String> parseList(String str) {
        return (str == null || str.trim().isEmpty()) ? Collections.emptyList() : Arrays.asList(str.split(","));
    }

    private List<Integer> parseColWidths(String colWidthStr) {
        List<Integer> colWidths = new ArrayList<>();
        for (String width : colWidthStr.split(",")) {
            try {
                colWidths.add(Integer.parseInt(width.trim()));
            } catch (NumberFormatException e) {
                colWidths.add(0);
            }
        }
        return colWidths;
    }
}
