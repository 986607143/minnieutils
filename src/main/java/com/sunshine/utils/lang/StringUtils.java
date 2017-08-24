package com.sunshine.utils.lang;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qiushengming
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{

    private static Logger logger = Logger.getLogger(StringUtils.class);

    public static void main(String[] args) {
        String[] s = stringSpilt("1234567890", 2);
        System.out.print(ArrayUtils.toString(s));
    }

    /**
     * 获取指定字符串出现的次数
     *
     * @param srcText  源字符串
     * @param findText 要查找的字符串
     * @return
     * @author qiushengming
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    /**
     * 查找第一次出现汉字的位置,包含汉字和中文符号</br>
     * 逻辑：通过正则去分割(split)字符串，取得第0个字符数据的长度</br>
     * 2017年7月5日</br>
     *
     * @param str 被操作的字符串
     * @return int 成功返回索引位置；失败返回-1；
     * @author qiushengming
     */
    public static int findHanZiFirstIndexOf(String str) {
        String reg = "[\u4e00-\u9fa5]";
        String regex = "[。，！？（）《》……、：——【】；’”‘“]";
        int index = -1;

        if (str.matches(".*" + reg + ".*")) {
            /*汉字*/
            index = str.split(reg)[0].length();
        } else if (str.matches(".*" + regex + ".*")) {
            /*中文符号*/
            index = str.split(regex)[0].length();
        }
        return index;
    }

    /**
     * 按照len长度分割s
     *
     * @param s   被分割的字符
     * @param len 长度
     * @return 分割后的数据
     */
    public static String[] stringSpilt(String s, int len) {
        if (isEmpty(s)) {
            return null;
        }


        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int start = i * len;
            int end = (i + 1) * len;
            try {
                sb.append(s.substring(start, end)).append("#");
            } catch (StringIndexOutOfBoundsException e) {
                logger.error(
                    "start >> " + start + "; end >> " + end + "; string >> "
                        + s);
            }
            if (end >= s.length()) {
                break;
            }
            i++;
        }
        return sb.toString().split("#");
    }

    public static boolean isEmpty(String s) {
        return org.apache.commons.lang.StringUtils.isEmpty(s);
    }

    /**
     * 将clob对象转换为string类型；clob == null，返回""；
     * @param clob java.sql.Clob
     * @return clob == null ? return "";
     */
    public static String clobToString(Clob clob) {
        if(clob == null){
            return "";
        }

        BufferedReader br; // 字符流
        StringBuilder sb = new StringBuilder(); // 转换的存储

        try {
            br = new BufferedReader(clob.getCharacterStream());
            String s = br.readLine();
            // 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            while (s != null) {
                sb.append(s);
                s = br.readLine();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            logger.error(e);
        }

        return sb.toString();
    }

    public static boolean equals(String s1, String s2){
        return org.apache.commons.lang.StringUtils.equals(s1, s2);
    }

}
