package com.jason.mt.util;

import com.google.common.collect.Lists;
import com.jason.mt.model.FlagModel;
import com.jason.mt.model.LoadTimeModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by jason on 2017/8/11.
 */
public class FileUtil {

    private static final String VAL_REF_LOAD_TIME_STR = "val_ref_load_time=";
    private static final String VAL_LOAD_TIME_STR = "val_load_time=";
    private static final String CT_POI_E_STR = "ct_poi_e=";
    private static final String CT_POI_STR = "ct_poi=";

    public static List<FlagModel> read(String filePathAndName) throws InvalidFormatException, IOException {

        List<FlagModel> flagList = Lists.newArrayList();

        File xlsFile = new File(filePathAndName);
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
            flagModel.setTimestamp((long) (r.getCell(1).getNumericCellValue()));
            flagModel.setCurPage(r.getCell(2).getStringCellValue());
            flagModel.setFromPage(r.getCell(3).getStringCellValue());
            flagModel.setLoadTimeModel(parseLoadTimeModel(r.getCell(4).getStringCellValue()));

            flagList.add(flagModel);
        }

        System.out.println(flagList.size());

        return flagList;

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
        str = str.replace("}", "");
        str = str.replace("{", "");

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
                loadTimeModel.setVal_load_time((int)Double.parseDouble(subStr.substring(subStr.indexOf(VAL_LOAD_TIME_STR) + VAL_LOAD_TIME_STR.length(), subStr.length())));
            } else if (subStr.contains(VAL_REF_LOAD_TIME_STR)) {
                loadTimeModel.setVal_ref_load_time((int)Double.parseDouble(subStr.substring(subStr.indexOf(VAL_REF_LOAD_TIME_STR) + VAL_REF_LOAD_TIME_STR.length(), subStr.length())));
            } if(subStr.contains(CT_POI_E_STR)) {
                loadTimeModel.setCt_poi_e(subStr.substring(subStr.indexOf(CT_POI_E_STR) + CT_POI_E_STR.length(), subStr.length()).trim());
            } if(subStr.contains(CT_POI_STR)) {
                loadTimeModel.setCt_poi_e(subStr.substring(subStr.indexOf(CT_POI_STR) + CT_POI_STR.length(), subStr.length()).trim());
            }
        }

        return loadTimeModel;
    }

    public static void writeFile(String filePathAndName, String lineContent) {
        FileWriter writer = null;
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            File f=new File(filePathAndName);
            writer = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(writer);
            pw.println(lineContent);
            pw.flush();
            writer.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {

            }
        }
    }
}
