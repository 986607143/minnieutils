package com.sunshine.utils;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class MinNieUtils {

    /**
     * 
     * 2017年4月30日
     * qiushengming
     * @return SortedMap<String,Charset>
     * <p>获取当前虚拟机可用的所有字符集</p>
     */
    public SortedMap<String, Charset> getCharset(){
        return Charset.availableCharsets();
    }
}
