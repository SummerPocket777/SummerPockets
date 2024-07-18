package com.sp.core.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateToStringUtils {

    private Date date;

    public static String getDate(Date date){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf1.format(date);
    }

    public static String getTime(Date date){
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
        return sdf1.format(date);
    }

    public static String getDay(Date date){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        return sdf1.format(date);
    }


}
