/*
 * ==============================================
 * (C)2016 Shanghai KingstarWinning Corporation. All rights reserved.
 * 项目名称： @TODO
 * 系统名称： minnie
 * 文件名称： RegexUtils
 * 注意事项：
 *
 *
 * Id: RegexUtils,v 1.6 2017/7/28 10:33 MinMin Exp $
 * ==============================================
 */
package com.sunshine.utils;

import java.util.regex.Pattern;

/**
 * <p></p>
 * <p>说明: </p>
 * <p>备注: </p>
 * <p>User : MinMin</p>
 * <p>Date : 2017/7/28 10:33</p>
 *
 * @author 公司名 : 上海金仕达卫宁软件科技有限公司（Shanghai KingStar WinningSoft LTD.） <br />
 *         变更履历 <br />
 * @version 1.0
 */
public class RegexUtils {

    public static boolean regex(String content, String regex){
        return Pattern.matches(regex, content);
    }
}

/* Copyright (C) 2017, 上海金仕达卫宁软件科技有限公司 Project, All Rights Reserved. */
