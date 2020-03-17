package com.llf.demo.java.string;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/10/23 11:07
 */
public class SBTest {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder("m_tag_s:音乐 OR m_tag_s:二次元 OR ");

        sb.delete(sb.length() - 3, sb.length());

        System.out.println(sb.toString());


    }

}
