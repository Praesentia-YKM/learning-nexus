package com.learn.spring.poi.excel.format.download.infrastructure;

import com.learn.spring.poi.excel.format.download.domain.CellStyleType;
import com.learn.spring.poi.excel.format.download.domain.ExcelFormatData;
import com.learn.spring.poi.excel.format.download.domain.ExcelFormulaGenerator;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelSheetCreator {
    private final Workbook workbook;
    private ExcelStyleManager styleManager;
    private ExcelValidationManager validationManager;

    public ExcelSheetCreator(Workbook workbook) {
        this.workbook = workbook;
        this.styleManager = new ExcelStyleManager(workbook);
        this.validationManager = new ExcelValidationManager();
    }

    public Sheet createSheet(ExcelFormatData formatData, Map<String, String> clientParamMap) {
        Sheet sheet = workbook.createSheet(
            formatData.findSheetNmByHeaderMap(clientParamMap.get("formatCd"))
        );

        // 열 너비 설정
        applyColumnWidths(sheet, formatData.getColWidths());

        // 헤더 설정
        addHeaderRowAndStyle(sheet, formatData.getHeaders(), formatData.getFormCds());

        // 첫 번째 행 데이터 추가
        addFirstRowData(sheet, formatData.getFirstRowCells());

        return sheet;
    }

    /**
     * formCd의 유형에 따라 데이터를 추가할지 여부를 결정하여 시트 생성
     */
    public void createSheetByFormCdRules(ExcelFormatData formatData, String formCd, List<Map<String, Object>> dataList) {
        final List<String> DEFAULT_HEADERS = List.of("코드명", "코드값");
        final List<Integer> DEFAULT_COLUMN_WIDTHS = List.of(5000, 3000);

        if (shouldUseCreateSheetWithData(formCd)) {
            createSheetWithData(
                formatData.findSheetNmByHeaderMap(formCd),
                formatData,
                dataList
            );
            return;
        }

        createSheet(formatData, Map.of("formatCd", formCd));
    }

    /**
     * 새로운 시트를 생성하고, 데이터를 추가한다.
     */
    public void createSheetWithData(String sheetName, ExcelFormatData formatData, List<Map<String, Object>> dataList) {
        Sheet sheet = workbook.createSheet(sheetName);
        addHeaderRowAndStyle(sheet, formatData.getHeaders(), sheetName);
        applyColumnWidths(sheet, formatData.getColWidths());
        addDataRows(sheet, dataList, sheetName);
    }

    private CellStyleType getStyleForFormCd(String formCd) {
        switch (formCd) {
            case "":
                return CellStyleType.CODE_NAME;
            case "DATE":
                return CellStyleType.DATE;
            case "ACCYY":
            case "BDGDGR":
            case "SN":
                return CellStyleType.REQUIRED;
            default:
                return CellStyleType.CODE;
        }
    }

    /**
     * 특정 formCd가 데이터 바인딩이 필요한 경우인지 체크
     */
    private boolean shouldUseCreateSheetWithData(String formCd) {
        return !List.of("", "DATE", "ACCYY", "BDGDGR", "SN", "YNDIV").contains(formCd);
    }

    private void addHeaderRowAndStyle(Sheet sheet, List<String> headers, List<String> formCds) {
        Row headerRow = sheet.createRow(0); // 첫번째 행

        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            for (String formCd : formCds) {
                cell.setCellStyle(styleManager.getStyle(getStyleForFormCd(formCd)));
            }
        }
    }

    private void addHeaderRowAndStyle(Sheet sheet, List<String> headers, String sheetName) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            sheetName = (i != 0) ? sheetName + "명" : sheetName;

            Cell cell = headerRow.createCell(i);
            cell.setCellValue(sheetName);
            cell.setCellStyle(styleManager.getStyle(CellStyleType.HEADER));
        }
    }

    private void addFirstRowData(Sheet sheet, List<String> firstRowCells) {
        Row firstRow = sheet.createRow(1); // 두 번째 행 (인덱스 1)

        for (int i = 0; i < firstRowCells.size(); i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(firstRowCells.get(i));
            cell.setCellStyle(styleManager.getStyle(CellStyleType.NORMAL));
        }
    }

    private void applyColumnWidths(Sheet sheet, List<Integer> colWidths) {
        for (int i = 0; i < colWidths.size(); i++) {
            sheet.setColumnWidth(i, colWidths.get(i) * 256);
        }
    }

    private void addDataRows(Sheet sheet, List<Map<String, Object>> dataList, String sheetName) {
        int rowNum = 1;

        for (Map<String, Object> rowData : dataList) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;

            for (Map.Entry<String, Object> entry : rowData.entrySet()) {
                String colName = entry.getKey().trim();
                Object value = entry.getValue();

                Cell cell = row.createCell(cellNum);
                cell.setCellStyle(styleManager.getStyle(CellStyleType.NORMAL));

                // 값 입력
                if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                } else {
                    cell.setCellValue(value.toString());
                }

                // 특정 컬럼에 대한 수식 적용 (VLOOKUP 적용 여부 반영)
                if ("SN".equalsIgnoreCase(colName)) {
                    cell.setCellFormula(
                        ExcelFormulaGenerator.generateCountIfFormula(getCellColNm(cellNum))
                    );
                } else if (needsVLookup(colName)) {
                    cell.setCellFormula(
                        ExcelFormulaGenerator.generateVLookupFormula(
                            getCellColNm(cellNum),
                            sheetName,
                            dataList.size()
                        )
                    );
                }

                cellNum++;
            }
        }
    }

    private void applyValidationRules(Sheet sheet, String formCd, int columnIdx, String vlookupFormula) {
        if ("YNDIV".equals(formCd)) {
            ExcelValidationManager.addYnComboValidation(sheet, columnIdx);
        } else {
            ExcelValidationManager.addDataValidation(sheet, columnIdx, vlookupFormula);
        }
    }

    /**
     * 컬럼 인덱스를 엑셀의 컬럼명(A, B, C...)으로 변환하는 메서드
     */
    private String getCellColNm(int colIdx) {
        String range = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return (colIdx >= 26 ? range.charAt(colIdx / 26 - 1) + "" : "") + range.charAt(colIdx % 26);
    }

    private boolean needsVLookup(String columnName) {
        // VLOOKUP이 필요 없는 컬럼 목록
        Set<String> excludedColumns = Set.of(
            "", "DATA", "ACCYY", "BDGDGR", "YNDIV"
        );

        // 제외된 컬럼이 아니면 VLOOKUP 적용
        return !excludedColumns.contains(columnName.toUpperCase());
    }

}

