package cn.wolfcode.service;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 代星宇
 * @date 2022/10/17 17:27
 * @Version cn.wolfcode.service
 */
public class Run {
    @Test
    public void TimeTest() throws Exception{
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY,23);
        instance.set(Calendar.MINUTE,59);
        instance.set(Calendar.SECOND,59);
        Date time = instance.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(time);
//        System.out.println(format.format(new Date()));
//        System.out.println(s);
        format(23,59,59);
        format(0,0,0);

    }
    void format(int hour,int minute,int second){
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY,hour);
        instance.set(Calendar.MINUTE,minute);
        instance.set(Calendar.SECOND,second);
        Date time = instance.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(time));
    }

    @Test
    public void dsTest() throws Exception{
        Map<String, String> map=new HashMap<>();
        map.put("1", "这是一");
        map.put("2", "这是二");
        map.put("3", "这是三");
        Set<String> keyset=map.keySet();
        Iterator<String> it=keyset.iterator();
        while(it.hasNext()){
            String key=it.next();
            System.out.println("key="+key+"\t"+"value="+map.get(key));
        }
    }
}
