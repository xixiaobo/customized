package com.swj.customized.tool;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 时间工具类
 * Created by xxb on 2018/10/26.
 */
public class TimeUtil {

    /**
     * 字符串转时间类
     * @param time
     * @param format 传入的字符串时间格式
     * @return
     */
    public static DateTime string2DateTime(String time, String format){
        DateTimeFormatter formats= DateTimeFormat.forPattern(format);
        return DateTime.parse(time,formats);
    }

    /**
     * 时间类转字符串
     * @param time
     * @param format 转换后的格式
     * @return
     */
    public static String dateTime2String(DateTime time, String format){
        return time.toString(format);
    }

    /**
     * 毫秒转时间
     * @param i
     * @return
     */
    public static String MillisToTime(Long i){
        long ms=i%1000;
        long allTime=i/1000;
        long ss = allTime%60;
        long h_m = (allTime - ss) / 60;
        long mm = h_m % 60;
        long hh = (h_m - mm) / 60;
        String h="";
        String m="";
        String s="";
        String sss="";
        if(hh>0){
            h=hh+"h";
        }
        if(mm>0){
            m=mm+"m";
        }
        s=ss+"s";
        sss=ms+"ms";
        return h+m+s+sss;
    }

    /**
     * 获取当前时间类
     * @return
     */
    public static DateTime getNewDate(){
        return DateTime.now();
    }

    /**
     * 获取当前时间字符串
     * @return
     */
    public static String getNewDateString(){
        return TimeUtil.dateTime2String(DateTime.now(),"yyyy-MM-dd HH:mm:ss");
    }

}
