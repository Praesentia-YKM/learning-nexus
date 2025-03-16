package com.learn.spring.poi.excel.format.download.domain.provider;

import com.learn.spring.poi.excel.format.download.CommonFileDAO;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 특정 업무 테이블에서 데이터를 조회하는 SheetProvider 구현체
 */
public class DynamicCodeSheetDataProvider implements SheetDataProvider {

    // final이 붙으면 무조건 생성자딴이던 필드딴이던 초기화 되어야함
    private CommonFileDAO commonFileDAO;

    @Override
    public List<Map<String, Object>> fetchData(String formCd, Map<String, String> clientParams) {
        List<Map<String, Object>> cdList = Collections.emptyList();

        try {
            final String normalizedFormCd = formCd.trim().replaceAll("[_\\s]", "").toLowerCase();

            // commonFileDAO에서 해당 메서드 동적으로 찾아서 실행
            if (!normalizedFormCd.isEmpty()) {
                Method targetMethod = Arrays.stream(commonFileDAO.getClass().getMethods())
                    .filter(m -> m.getName().equalsIgnoreCase("select" + normalizedFormCd))
                    .filter(m -> m.getParameterCount() == 1 && m.getParameterTypes()[0].equals(Map.class))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("메서드를 찾을 수 없습니다: select" + normalizedFormCd));

                cdList = (List<Map<String, Object>>) targetMethod.invoke(commonFileDAO, clientParams);
            }

        } catch (Exception e) {
            throw new RuntimeException("해당 formCd에 대한 동적 쿼리 메서드를 찾을 수 없습니다: " + formCd, e);
        }

        return cdList;
    }
}