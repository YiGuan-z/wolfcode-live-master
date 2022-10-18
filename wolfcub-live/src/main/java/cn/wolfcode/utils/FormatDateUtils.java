package cn.wolfcode.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 代星宇
 * @date 2022/10/17 17:39
 * @Version cn.wolfcode.utils
 */
public class FormatDateUtils {


    public static Date maxNowTime(){
        return format(23,59,59);
    }

    public static Date minNowTime(){
        return format(0,0,0);
    }


    public static Date format(int hour,int minute,int second){
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY,hour);
        instance.set(Calendar.MINUTE,minute);
        instance.set(Calendar.SECOND,second);
        return instance.getTime();
    }
}
