package com.llf.demo.java.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/11/15 10:30
 */
public class DateTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = format.parse("2019-11-05 23:59:59");

        boolean aa = true;

        Calendar instance = Calendar.getInstance();
        instance.setTime(createTime);
        int year = instance.get(Calendar.YEAR);
        if (year < 2019){
            aa =  false;
        }

        //超过11月15号，不符合报名
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        if (month >= Calendar.NOVEMBER && day > 15){
            aa =  false;
        }

        System.out.println(aa);

    }

}
