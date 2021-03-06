/**
 * com.luunbeloved.utils.UnicodeUtils.java
 *
 * @author:Luun
 * @emile：583853240@qq.com
 * @date:2016-8-25下午5:27:10
 * @version 1.0
 */
package com.sunshine.utils;

public class UnicodeUtils {

    /**
     *
     * 2017年4月30日
     * qiushengming
     * @param unicode
     * @return String
     * <p>unicode编码转中文，单个字符[111]</p>
     */
    public static String unicodeToString(Integer unicode) {
        StringBuffer sb = new StringBuffer();

        int data = Integer.parseInt(unicode.toString(), 16);
        sb.append((char) data);
        //            sb.append(unicode.substring(4));
        return sb.toString();
    }

    /**
     *
     * 2017年4月30日
     * qiushengming
     * @param unicode
     * @return String
     * <p>unicode编码转中文，格式[\u1111]</p>
     */
    public static String unicodeToString(String unicode) {
        StringBuffer sb = new StringBuffer();

        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i].substring(0, 4), 16);
            sb.append((char) data);
            sb.append(hex[i].substring(4));
        }
        return sb.toString();
    }

    public static String stringToUnicode(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); // 取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);
            j = (c & 0xFF); // 取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1)
                sb.append("0");
            sb.append(tmp);

        }
        return (new String(sb));
    }
}
