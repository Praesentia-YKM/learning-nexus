package com.learn.spring.poi.excel.format.download;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CommonFileDAO {
    List<Map<String, String>> selectAllExcelForms();
}