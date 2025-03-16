package com.learn.spring.poi.excel.format.download.application;

import com.learn.spring.poi.excel.format.download.domain.ExcelFormatData;
import com.learn.spring.poi.excel.format.download.domain.ExcelFormatLoader;
import com.learn.spring.poi.excel.format.download.domain.provider.SheetDataProvider;
import com.learn.spring.poi.excel.format.download.domain.provider.SheetDataProviderFactory;
import com.learn.spring.poi.excel.format.download.infrastructure.ExcelSheetCreator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelFormatService {

    private final ExcelFormatLoader formatLoader;
    private final SheetDataProviderFactory sheetDataProviderFactory;

    @Autowired
    public ExcelFormatService(ExcelFormatLoader formatLoader, SheetDataProvider sheetDataProvider, SheetDataProviderFactory sheetDataProviderFactory) {
        this.formatLoader = formatLoader;
        this.sheetDataProviderFactory = sheetDataProviderFactory;
    }

    public String createExcelFile(Map<String, String> params, String rootPath) throws Exception {
        // 업무 파라미터 역직렬화
        Map<String, String> clientParamMap = new HashMap<>();
        deSerializedClientParamsTo(params, clientParamMap);

        // 양식 포맷 데이터 로드
        formatLoader.loadExcelFormats();
        String formatCd = params.get("formatCd");
        ExcelFormatData formatData = formatLoader.getExcelFormat(formatCd);

        // formCd조건에 따라 동적으로 시트 생성
        Workbook workbook = new XSSFWorkbook();
        ExcelSheetCreator sheetCreator = new ExcelSheetCreator(workbook);
        createSheet(formatData, sheetCreator, clientParamMap);

        String filePath = saveWorkbook(workbook, rootPath, formatCd);
        return filePath;
    }

    private void createSheet(ExcelFormatData formatData, ExcelSheetCreator sheetCreator, Map<String, String> clientParamMap) {
        if (formatData.getFormCds().isEmpty()) {
            sheetCreator.createSheet(formatData, clientParamMap);
        } else {
            // formCds가 존재할 경우 각 시트를 동적으로 생성
            for (String formCd : formatData.getFormCds()) {
                SheetDataProvider provider = sheetDataProviderFactory.getProvider(formCd, clientParamMap);

                sheetCreator.createSheetByFormCdRules(
                    formatData,
                    formCd,
                    provider.fetchData(formCd, clientParamMap)
                );
            }
        }
    }

    private static void deSerializedClientParamsTo(Map<String, String> param, Map<String, String> clientParamMap) {
        JSONObject jObject = new JSONObject(param.get("param").replace("&quot;", "'"));
        for (Object key : jObject.keySet()) {
            clientParamMap.put(key.toString(), jObject.get(key.toString()).toString());
        }
    }

    private String saveWorkbook(Workbook workbook, String rootPath, String formatCd) {
        String dirPath = rootPath + "/formatFile/";
        makeDirectoryIfNotExists(dirPath);

        String filePath = dirPath + formatCd + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException("엑셀 파일 저장 중 오류 발생", e);
        }

        return filePath;
    }

    private void makeDirectoryIfNotExists(String path) {
        File dir = new File(path);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("디렉토리 생성에 실패했습니다: " + path);
        }
    }

}
