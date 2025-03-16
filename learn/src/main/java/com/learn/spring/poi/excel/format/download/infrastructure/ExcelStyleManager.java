package com.learn.spring.poi.excel.format.download.infrastructure;

import com.learn.spring.poi.excel.format.download.domain.CellStyleType;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.Map;

/**
 * 셀타입 별 스타일을 꺼내서 적용할 수 있다.
 */
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import java.util.EnumMap;

public class ExcelStyleManager {
    private final Map<CellStyleType, CellStyle> styles = new EnumMap<>(CellStyleType.class);

    public ExcelStyleManager(Workbook workbook) {
        initializeStyles(workbook);
    }

    private void initializeStyles(Workbook workbook) {
        CreationHelper createHelper = workbook.getCreationHelper();

        styles.put(CellStyleType.HEADER, createCellStyle(workbook, IndexedColors.LIME, true, BorderStyle.THIN));
        styles.put(CellStyleType.NORMAL, createCellStyle(workbook, null, false, BorderStyle.THIN));
        styles.put(CellStyleType.CODE, createCellStyle(workbook, IndexedColors.GREY_25_PERCENT, true, BorderStyle.THIN));
        styles.put(CellStyleType.DATE, createDateCellStyle(workbook, createHelper));
        styles.put(CellStyleType.REQUIRED, createCellStyle(workbook, IndexedColors.TAN, true, BorderStyle.THIN));
    }

    private CellStyle createCellStyle(Workbook workbook, IndexedColors color, boolean locked, BorderStyle borderStyle) {
        CellStyle style = workbook.createCellStyle();

        if (color != null) {
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }

        style.setLocked(locked);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderRight(borderStyle);
        style.setBorderTop(borderStyle);
        return style;
    }

    private CellStyle createDateCellStyle(Workbook workbook, CreationHelper createHelper) {
        CellStyle style = createCellStyle(workbook, null, false, BorderStyle.THIN);
        style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
        return style;
    }

    public CellStyle getStyle(CellStyleType type) {
        return styles.get(type);
    }
}
