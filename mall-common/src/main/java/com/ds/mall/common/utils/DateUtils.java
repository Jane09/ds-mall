package com.ds.mall.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tb
 * @date 2019/1/7 14:43
 */
public final class DateUtils {

    /** 时间格式(yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** 时间格式(yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static String format(long timestamp, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        return sdf.format(c.getTime());
    }
}
