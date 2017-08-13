package com.jason.mt;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jason.mt.constant.PageIdentifyConstant;
import com.jason.mt.model.FlagModel;
import com.jason.mt.util.FileUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jason on 2017/7/24.
 */
public class Main {
    private static List<FlagModel> flagList = new ArrayList<FlagModel>();
    private static Set<FlagModel> flagSet = new HashSet<FlagModel>();


    private static final String mt_android_detail = "/Users/jason/workspace/mt/data/source/mt-android-详情页.xlsx";
    private static final String mt_android_list = "/Users/jason/workspace/mt/data/source/mt-安卓-列表页.xlsx";
    private static final String mt_ios_detail = "/Users/jason/workspace/mt/data/source/mt-ios-详情页.xlsx";
//    private static final String mt_ios_list = "/Users/jason/workspace/mt/data/source/mt-iphone-列表页.xlsx";

    private static final String mt_android_deatil_process = "/Users/jason/workspace/mt/data/result/mt_android_detail";
    private static final String mt_android_list_process = "/Users/jason/workspace/mt/data/result/mt_android_list";
    private static final String mt_ios_deatil_process = "/Users/jason/workspace/mt/data/result/mt_ios_detail";
//    private static final String mt_ios_list_process = "/Users/jason/workspace/mt/data/result/mt_ios_list";


    static public void main(String[] args) throws InvalidFormatException, IOException {
//        List<FlagModel> totalFlagList = FileUtil.read(mt_android_detail);
//        List<FlagModel> totalFlagList = FileUtil.read(mt_android_list);
        List<FlagModel> totalFlagList = FileUtil.read(mt_ios_detail);
//        List<FlagModel> totalFlagList = FileUtil.read(mt_ios_list);
        List<FlagModel> flagList = filter(totalFlagList);

        for (FlagModel flagModel : flagList) {
//            FileUtil.writeFile(mt_android_deatil_process, convertString(flagModel));
//            FileUtil.writeFile(mt_android_list_process, convertString(flagModel));
            FileUtil.writeFile(mt_ios_deatil_process, convertString(flagModel));
//            FileUtil.writeFile(mt_ios_list_process, convertString(flagModel));
        }
    }


    static private List<FlagModel> filter(List<FlagModel> flagList) {
        List<FlagModel> rst = Lists.newArrayList();

        int depulicate = 0;

        for (FlagModel item : flagList) {
            if((!item.getFromPage().equalsIgnoreCase(PageIdentifyConstant.mt_ios_list_identify_1) && !item.getFromPage().equalsIgnoreCase(PageIdentifyConstant.mt_ios_list_identify_2))
                    || item.getLoadTimeModel() == null) {
//            if (item.getLoadTimeModel() == null || item.getLoadTimeModel().getVal_load_time() < 1 || Strings.isNullOrEmpty(item.getLoadTimeModel().getCt_poi_e() )) {
                continue;
            }

            if(flagSet.contains(item)) {
                depulicate ++;
                continue;
            }

            flagSet.add(item);

            rst.add(item);
        }

        return rst;
    }

    static private String convertString(FlagModel flagModel) {
        StringBuilder sb = new StringBuilder();
        sb.append(flagModel.getCurPage());
        sb.append(" ");
        sb.append(flagModel.getFromPage());
        sb.append(" ");
        sb.append(flagModel.getLoadTimeModel().getVal_load_time());
        sb.append(" ");
        sb.append(flagModel.getLoadTimeModel().getVal_ref_load_time());
        sb.append("\r");

        return sb.toString();
    }


}
