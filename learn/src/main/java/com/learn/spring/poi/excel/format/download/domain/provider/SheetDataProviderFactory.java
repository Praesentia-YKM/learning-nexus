package com.learn.spring.poi.excel.format.download.domain.provider;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * SheetDataProvider 구현체를 formCd에 따라 동적으로 선택하는 팩토리 클래스
 */
@Component
public class SheetDataProviderFactory {

    /**
     * formCd에 따라 적절한 SheetDataProvider 구현체 반환
     */
    public SheetDataProvider getProvider(String formCd, Map<String, String> params) {
        if (shouldUseCommonCodeProvider(formCd, params)) {
            return new CommonCodeSheetDataProvider();
        }
        return new DynamicCodeSheetDataProvider();
    }

    /**
     * 공통 코드 테이블을 사용할지 여부를 결정하는 메서드
     */
    private boolean shouldUseCommonCodeProvider(String formCd, Map<String, String> params) {
        // formatCd가 공통코드 테이블을 참조하는지 확인
        String formatCd = params.get("formatCd");

        // 특정 formatCd가 공통 코드 테이블을 참조하는지 확인하는 추가 조건 (화이트리스트 기반)
        Map<String, List<String>> columnWhiteList = getColumnWhiteList();
        return columnWhiteList.containsKey(formatCd) && columnWhiteList.get(formatCd).contains(formCd);
    }

    /**
     * 특정 formatCd에서 SYS_COM_DTS 테이블을 바라보는 formCd 목록을 정의한 화이트리스트
     */
    private Map<String, List<String>> getColumnWhiteList() {
        Map<String, List<String>> columnWhiteList = new HashMap<>();
        columnWhiteList.put(
            "HCMHIM171_1",
            Arrays.asList("ISEFRGNR_SE", "SEXDSTN", "LAST_ACDMCR", "MNGPRP_NLTY", "RPTPRPOS_NLTY", "RESIDE_NATION_CD")
        );
        columnWhiteList.put(
            "HCMHIM171_2",
            Arrays.asList("HFFC_SE", "JSSFC", "POS_CD", "WORKTAK", "RSPOFC", "DTY", "S22", "JBGP")
        );
        columnWhiteList.put(
            "HCMHIM171_3", Arrays.asList("MTRSC_SE", "MSCL", "CLSS", "BNCTY", "DMBLZ_SE")
        );
        columnWhiteList.put(
            "HCMHIM171_4",
            Arrays.asList("RELATE", "TROBL_SE", "ISEFRGNR_SE", "DGRI_SE")
        );
        columnWhiteList.put(
            "HCMHIM171_8",
            Collections.singletonList("RWRPNS_SE")
        );
        columnWhiteList.put(
            "HCMHIM171_9",
            Collections.singletonList("FGLG_DIV")
        );
        columnWhiteList.put(
            "HCMHIM171_10",
            Arrays.asList("RWDMRT_DIV", "ATHZER_RELATE")
        );
        columnWhiteList.put(
            "HCMHIM171_11", Arrays.asList("TROBL_ORT_SMBOL", "TROBL_GRAD")
        );

        return columnWhiteList;
    }

}