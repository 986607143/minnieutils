package com.sunshine.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

public class HanyuToPinYinUtils {

    private static Logger logger = Logger.getLogger(HanyuToPinYinUtils.class);

    public static void main(String[] args) {

    }

    /**
     * 暂不处理多音字，只返回结果的第一个拼音码。
     *
     * @param args 汉字
     * @return 返回拼音码
     */
    public static String[] hanYuToPinYin(String args) {
        //初始化汉语拼音输出格式
        HanyuPinyinOutputFormat py = new HanyuPinyinOutputFormat();
        py.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        py.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        py.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] src1 = args.toCharArray();
        String[] result = new String[src1.length];
        String regex = "[\\u4E00-\\u9FA5]";
        try {
            for (int i = 0; i < src1.length; i++) {
                if (Character.toString(src1[i]).matches(regex)) {
                    String[] temp =
                        PinyinHelper.toHanyuPinyinStringArray(src1[i], py);
                    result[i] = temp[0];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        return ArrayUtils.romveNull(result);
    }

    /**
     * 获取汉语拼音首字母
     *
     * @param args 被处理
     * @return 拼音码
     */
    public static String getHanYuPinYinFist(String args)
        throws UnsupportedEncodingException {
        String[] pinyin = hanYuToPinYin(args);
        StringBuilder sb = new StringBuilder();
        for (String s : pinyin) {
            sb.append(s.charAt(0));
        }
        return sb.toString();
    }
}
