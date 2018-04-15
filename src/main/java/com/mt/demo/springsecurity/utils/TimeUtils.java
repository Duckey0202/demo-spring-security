package com.mt.demo.springsecurity.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * TimeUtils
 *
 * @author MT.LUO
 * 2018/3/7 18:23
 * @Description:
 */
public class TimeUtils {
    /**
     * input: 20180307T091806Z
     * output date
     * */
    public static Date StringToData(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        try {
            Date date = dateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
