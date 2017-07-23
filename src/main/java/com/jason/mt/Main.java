package com.jason.mt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.jason.mt.model.FlagModel;
import com.jason.mt.model.LoadTimeModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Created by jason on 2017/7/24.
 */
public class Main {
    private static final List<FlagModel> flagList = new ArrayList<FlagModel>();
    private static final String VAL_REF_LOAD_TIME_STR = "val_ref_load_time=";
    private static final String VAL_LOAD_TIME_STR = "val_load_time=";

    static public void main(String[] args) throws InvalidFormatException, IOException {
        read();
    }

    public static void read() throws InvalidFormatException, IOException {
        File xlsFile = new File("/Users/jason/Documents/工作/数据/custom-zhuzhiqing/mt/详情_4067000_20170721_170718.xlsx");
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历工作表
        Sheet sheet = workbook.getSheetAt(0);
        // 获得行数
        int rows = sheet.getLastRowNum() + 1;

        for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
            Row r = sheet.getRow(rowIndex);

            FlagModel flagModel = new FlagModel();
            flagModel.setTimestamp((long) (r.getCell(0).getNumericCellValue()));
            flagModel.setCurPage(r.getCell(1).getStringCellValue());
            flagModel.setFromPage(r.getCell(2).getStringCellValue());
            flagModel.setLoadTimeModel(parseLoadTimeModel(r.getCell(3).getStringCellValue()));

            flagList.add(flagModel);
        }

        System.out.println(flagList.size());

//        for (int i = 0; i < sheetCount; i++) {
//            Sheet sheet = workbook.getSheetAt(i);
//            // 获得行数
//            int rows = sheet.getLastRowNum() + 1;
//            // 获得列数，先获得一行，在得到改行列数
//            Row tmp = sheet.getRow(0);
//            if (tmp == null) {
//                continue;
//            }
//            int cols = tmp.getPhysicalNumberOfCells();
//            // 读取数据
//            for (int row = 0; row < rows; row++) {
//                Row r = sheet.getRow(row);
//                for (int col = 0; col < cols; col++) {
//                    System.out.printf("%10s", r.getCell(col).getStringCellValue());
//                }
//                System.out.println();
//            }
//        }
    }

    private static LoadTimeModel parseLoadTimeModel(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        str = str.replace(" ", "");
        str = str.replace("}","");
        str = str.replace("{","");

        if (!(str.contains(VAL_REF_LOAD_TIME_STR) && str.contains(VAL_LOAD_TIME_STR))) {
            return null;
        }

        List<String> strList = Lists.newArrayList(str.split(","));
        if (CollectionUtils.isEmpty(strList)) {
            return null;
        }

        LoadTimeModel loadTimeModel = new LoadTimeModel();
        for (String subStr : strList) {
            if (subStr.contains(VAL_LOAD_TIME_STR)) {
                loadTimeModel.setVal_load_time(Double.parseDouble(subStr.substring(subStr.indexOf(VAL_LOAD_TIME_STR) + VAL_LOAD_TIME_STR.length(), subStr.length())));
            } else if (subStr.contains(VAL_REF_LOAD_TIME_STR)) {
                loadTimeModel.setVal_ref_load_time(Double.parseDouble(subStr.substring(subStr.indexOf(VAL_REF_LOAD_TIME_STR) + VAL_REF_LOAD_TIME_STR.length(), subStr.length())));
            }
        }

        return loadTimeModel;
    }
}
