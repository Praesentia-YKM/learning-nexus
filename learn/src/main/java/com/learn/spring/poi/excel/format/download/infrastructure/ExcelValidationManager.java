package com.learn.spring.poi.excel.format.download.infrastructure;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Component;

@Component
public class ExcelValidationManager {

    /**
      * Y/N 콤보 박스 유효성 검증 추가
      */
     public static void addYnComboValidation(Sheet sheet, int columnIdx) {
         DataValidationHelper validationHelper = sheet.getDataValidationHelper();
         CellRangeAddressList addressList = new CellRangeAddressList(1, 100, columnIdx, columnIdx);

         String[] ynCombo = {"Y", "N"};
         DataValidationConstraint ynConstraint = validationHelper.createExplicitListConstraint(ynCombo);
         DataValidation ynValidation = validationHelper.createValidation(ynConstraint, addressList);
         ynValidation.setSuppressDropDownArrow(true);
         ynValidation.createErrorBox("알림", "Y 또는 N만 입력 가능합니다.");
         ynValidation.setShowErrorBox(true);
         ynValidation.setEmptyCellAllowed(false);

         sheet.addValidationData(ynValidation);
     }

     /**
      * VLOOKUP 기반 데이터 검증 추가
      */
     public static void addDataValidation(Sheet sheet, int columnIdx, String vlookupFormula) {
         DataValidationHelper validationHelper = sheet.getDataValidationHelper();
         CellRangeAddressList addressList = new CellRangeAddressList(1, 100, columnIdx, columnIdx);

         DataValidationConstraint constraint = validationHelper.createFormulaListConstraint(vlookupFormula);
         DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
         dataValidation.setSuppressDropDownArrow(true);
         dataValidation.createErrorBox("알림", "입력값이 올바르지 않습니다.");
         dataValidation.setShowErrorBox(true);
         dataValidation.setEmptyCellAllowed(false);

         sheet.addValidationData(dataValidation);
     }
}
