package com.llf.demo.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/11/19 11:37
 */
public class SetTest {


    public static void main(String[] args) {

        List<String> list = new ArrayList<String>(){{
           add("0");
           add("1");
           add("2");
           add("3");
           add("4");
           add("5");
           add("6");
           add("7");
           add("8");
           add("9");
        }};

        Collections.shuffle(list);

        for (String s : list) {
            System.out.println(s);
        }
    }

}
