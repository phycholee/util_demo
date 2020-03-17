package com.llf.demo.java.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/10/22 15:42
 */
public class DateFormatTest {

//    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("2018-10-22 HH:mm:ss");
//
//        Date date = format.parse("2018-10-22 10:10:00");
//
//        System.out.println(date);
//    }

    public static void main(String[] args) {

        Date now = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(now);
        int year = instance.get(Calendar.YEAR);

        System.out.println(year);

    }

    /**
     * 打乱字符串
     * @param str
     * @param i
     * @return
     */
    private static String disruptString(String str, int i){
        if (str == null || i < 0 || str.length() <= i) {
            return str;
        }
        char[] chars = str.toCharArray();
        reverseCharArray(chars, 0, i - 1);
        reverseCharArray(chars, i + 1, chars.length - 1);
        reverseCharArray(chars, 0, chars.length - 1);
        return new String(chars);
    }

    /**
     * 交换chars数组位置
     * @param chars
     * @param begin
     * @param end
     */
    private static void reverseCharArray(char[] chars, int begin, int end) {
        char temp;
        while (begin < end) {
            temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;
            begin++;
            end--;
        }
    }

}
