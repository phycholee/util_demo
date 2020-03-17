package com.llf.demo.java.map;

import java.util.TreeMap;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/4/17 18:05
 */
public class MapTest {


    public static void main(String[] args) {


        TreeMap<String, String> treeMap = new TreeMap<>((o1, o2) -> {

            if (o1.length() < o2.length()){
                return -1;
            } else if (o1.length() == o2.length()){
                return 0;
            }
            return 1;
        });

        treeMap.put("gggg", "gggg");
        treeMap.put("rrrrr", "rrrrr");
        treeMap.put("bb", "bb");
        treeMap.put("fff", "fff");
        treeMap.put("a", "a");


        System.out.println(treeMap);
    }

}
