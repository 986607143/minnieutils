package com.sunshine.action;

import com.sunshine.utils.ReaderExcel;
import com.sunshine.utils.poi.WriteExecl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>KBMS2.2需求写入Excel插入输入分隔</p>
 *
 * @author qiushengming
 */
public class KBMSAction {

    private List<Map<String, String>> list =
        new ArrayList<Map<String, String>>();
    private String filepath =
        System.getProperty("user.dir") + "\\Excel\\知识点样例数据导出.xlsx";
    private String filepath1 =
        System.getProperty("user.dir") + "\\Excel\\知识点样例数据导出1.xlsx";


    public static void main(String[] args) {
        KBMSAction kbms = new KBMSAction();
        try {
            kbms.action();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void action() throws Exception {
        ReaderExcel readerExcel = new ReaderExcel();
        WriteExecl writeExcel = new WriteExecl(
            "D:\\Temp\\数据导出\\2017-04-27_130155\\开发_收费类别统计数据.xlsx");
        readerExcel
            .readerData("D:\\Temp\\数据导出\\2017-04-27_130155\\临床_收费类别统计数据.xlsx");
        List<Map<String, String>> listResult = readerExcel.getResult();
        List<Map<String, String>> listWriter =
            new ArrayList<Map<String, String>>();
        for (Map<String, String> map : listResult) {
            String value = map.get("A");
            String[] valueArr = value.split("\\|");
            for (String v : valueArr) {
                String[] temp = v.split("_");
                map.put(temp[1], temp[0]);
            }
            listWriter.add(newMap(map));
        }
        //    writeExcel.writeData(listWriter);
    }

    private Map<String, String> newMap(Map<String, String> map) {
        Map<String, String> temp = new HashMap<String, String>() {
            /**
             * 2017年4月9日下午12:27:41
             * qiushengming
             *
             */
            private static final long serialVersionUID = -5279103562466394825L;

            {
                put("11", "");
                put("12", "");
                put("13", "");
                put("14", "");
                put("21", "");
                put("22", "");
                put("23", "");
                put("24", "");
                put("25", "");
                put("26", "");
                put("27", "");
                put("28", "");
                put("29", "");
                put("31", "");
                put("32", "");
                put("33", "");
                put("34", "");
                put("35", "");
                put("36", "");
                put("37", "");
                put("38", "");
                put("81", "");
                put("91", "");
                put("92", "");
                put("93", "");
                put("94", "");
                put("95", "");
            }
        };
        temp.putAll(map);
        return temp;
    }

}
