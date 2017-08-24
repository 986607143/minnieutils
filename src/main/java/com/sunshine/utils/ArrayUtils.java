/*
 * ==============================================
 * (C)2016 Shanghai KingstarWinning Corporation. All rights reserved.
 * 项目名称： @TODO
 * 系统名称： minnie
 * 文件名称： ArrrayUtils
 * 注意事项：
 *
 *
 * Id: ArrrayUtils,v 1.6 2017年4月6日下午2:18:31 MinMin Exp $
 * ==============================================
 */
package com.sunshine.utils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Created with IntelliJ IDEA</p>
 * <p>User : MinMin</p>
 * <p>Date : 2017/7/20 16:01</p>
 * <p>to change this template user file | settings | file templates
 * <p>Description</p>
 * <p>@version 1.0</p>
 * <p>@author 公司名 : 上海金仕达卫宁软件科技有限公司（Shanghai KingStar WinningSoft LTD.） <br />
 * 变更履历 <br />
 */
public class ArrayUtils {

    public static void main(String[] args) {
        String[] s = {"1","","2",null,"","3"};
        System.out.print(org.apache.commons.lang.ArrayUtils.toString(romveNull(s)));
    }

    public static String[] romveNull(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            if (StringUtils.isNotEmpty(s)) {
                sb.append(s).append("#");
            }
        }

        return sb.toString().split("#");
    }

    public static String toString(Object[] args) {
        return org.apache.commons.lang.ArrayUtils.toString(args);
    }

    public static boolean isEmpty(Object[] objects){
        return org.apache.commons.lang.ArrayUtils.isEmpty(objects);
    }
}

/* Copyright (C) 2016, 上海金仕达卫宁软件科技有限公司 Project, All Rights Reserved. */
