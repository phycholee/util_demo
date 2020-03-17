package com.llf.demo.java.string;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/8/20 14:49
 */
public class ReverseAlgorithm {

    /**
     * 将www google com翻转为com google www
     */


    /**
     * 翻转字符串
     * @param chars
     * @param start
     * @param end
     * @return
     */
    private static char[] reverse(char[] chars, int start, int end){

        if (chars == null || chars.length == 0 || start < 0 || end >= chars.length || start >= end){
            return chars;
        }

        while (start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }

        return chars;
    }

    public static String solution(String str){

        if (str == null || str.trim().length() <=0){
            return null;
        }

        int length = str.length();
        char[] chars = reverse(str.toCharArray(), 0, length - 1);

        int start = 0, end = 0;

        while (start < length){
            if (chars[start] == ' '){
                start ++;
                end ++;
            } else if (end == length || chars[end] == ' '){

                chars = reverse(chars, start, end - 1);
                start = end++;
            } else {
                end++;
            }
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        String str = solution("www google com");
        System.out.println(str);
    }
}
