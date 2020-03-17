package com.llf.demo.java.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/10/11 2:26 PM
 */
public class ListTest {

    public static void main(String[] args) {

//        List<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(2);
//        list.add(17);
//        list.add(5);
//
//        List<String> list2 = new ArrayList<>();
//        list2.add("zz");
//        list2.add("cc");
//        list2.add("ee");
//        list2.add("bb");
//
//        final Map<String, Integer> unionIdMap = new HashMap<>();
//        unionIdMap.put("cc", 3);
//        unionIdMap.put("bb", 2);
//        unionIdMap.put("zz", 17);
//        unionIdMap.put("ee", 5);
//
//
//        list2.sort((o1, o2) -> {
//
////            Integer i1 = unionIdMap.get(o1);
////            Integer i2 = unionIdMap.get(o2);
////
////            return list.indexOf(i1) - list.indexOf(i2);
//
//            double s1 = 20.0;
//            double s2 = 0.0;
//
//            double result = s1 - s2;
//            return result == 0 ? 0 : (result < 0 ? -1 : 1);
//        });
//
//        System.out.println(list2);

        test1();
    }

    public static void test1(){

        final Map<Long, Double> scoreMap = new HashMap<>();
        scoreMap.put(10013L, 0.0);
        scoreMap.put(10012L, 0.0);
        scoreMap.put(10015L, 0.0);
        scoreMap.put(10014L, 20.0);
        scoreMap.put(10009L, 0.0);
        scoreMap.put(10008L, 0.0);
        scoreMap.put(10011L, 0.0);
        scoreMap.put(10010L, 0.0);
        scoreMap.put(10005L, 18.0);
        scoreMap.put(10004L, 0.0);
        scoreMap.put(10007L, 0.0);
        scoreMap.put(10006L, 0.0);
        scoreMap.put(10001L, 0.0);
        scoreMap.put(10003L, 0.0);
        scoreMap.put(10002L, 17.0);
        scoreMap.put(10022L, 0.0);
        scoreMap.put(10023L, 0.0);
        scoreMap.put(10020L, 0.0);
        scoreMap.put(10021L, 19.0);
        scoreMap.put(10018L, 0.0);
        scoreMap.put(10019L, 0.0);
        scoreMap.put(10016L, 0.0);
        scoreMap.put(10017L, 0.0);



        List<Long> list2 = new ArrayList<>();
        list2.add(10013L);
        list2.add(10012L);
        list2.add(10015L);
        list2.add(10014L);
        list2.add(10009L);
        list2.add(10008L);
        list2.add(10011L);
        list2.add(10010L);
        list2.add(10005L);
        list2.add(10004L);
        list2.add(10007L);
        list2.add(10006L);
        list2.add(10001L);
        list2.add(10003L);
        list2.add(10002L);
        list2.add(10022L);
        list2.add(10023L);
        list2.add(10020L);
        list2.add(10021L);
        list2.add(10018L);
        list2.add(10019L);
        list2.add(10016L);
        list2.add(10017L);

        list2.sort((o1, o2) -> {

            double s1 = scoreMap.get(o1);
            double s2 = scoreMap.get(o2);

            double result = s1 - s2;
            return result == 0 ? 0 : (result > 0 ? -1 : 1);
        });

        System.out.println(list2);

    }

}
