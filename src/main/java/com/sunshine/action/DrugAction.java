package com.sunshine.action;

import com.sunshine.utils.FileUtils;
import com.sunshine.utils.ReaderExcel;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DrugAction {

    private static Logger logger = Logger.getLogger(DrugAction.class);

    public static void action() {

        List<File> listFile =
            FileUtils.getFile(System.getProperty("user.dir") + "\\Excel");
        ReaderExcel readerExcel = new ReaderExcel();

        for (File f : listFile) {
            try {
                readerExcel.readerData(f);
                toDealWith(readerExcel);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    /**
     * @param readerExcel <p>数据处理</p>
     * @author qiushengming
     * @data 2017年3月19日 下午8:33:53
     */
    private static void toDealWith(ReaderExcel readerExcel) {
        for (Map<String, String> map : readerExcel.getResult()) {
            for (String key : readerExcel.getTitleResult()) {
                String temp = map.get(key);
                logger.info(temp);
                /**
                 * 此处处理每个单元格中的值 TODO
                 */
            }
        }
    }
}
