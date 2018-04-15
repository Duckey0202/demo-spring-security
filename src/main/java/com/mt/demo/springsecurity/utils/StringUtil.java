package com.mt.demo.springsecurity.utils;

/**
 * StringUtil
 *
 * @author MT.LUO
 * 2018/2/24 9:52
 * @Description:
 */
public class StringUtil {
    public static boolean strIsNullOrEmpty(String s) {
        return (null == s || s.trim().length() < 1);
    }
}
