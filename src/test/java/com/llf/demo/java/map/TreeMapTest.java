package com.llf.demo.java.map;

import java.util.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/5/31 10:05
 */
public class TreeMapTest {


    public static void main(String[] args) {


        Map<Long, List<HomeworkDto>> hashMap = buildMap();

        Map<Long, List<HomeworkDto>> treeMap = new TreeMap<>((o1, o2) -> {
            Long t1 = getStartTime(o1);
            Long t2 = getStartTime(o2);

            if (t1 < t2){
                return -1;
            } else if (t1.equals(t2)){
                return 0;
            }
            return 1;
        });

        treeMap.putAll(hashMap);

        System.out.println("hashMap=========" + hashMap);
        System.out.println("hashMap.size====" + hashMap.size());
        System.out.println("treeMap=========" + treeMap);
        System.out.println("treeMap.size====" + treeMap.size());

    }

    private static Long getStartTime(Long topicId){

        switch (topicId.toString()){
            case "2000004660741715":
                return 1L;
            case "2000004660741730":
                return 1L;
            case "2000004660741702":
                return 3L;
            case "2000004660741727":
                return 3L;
            case "2000004660741723":
                return 5L;
            case "2000004660741706":
                return 6L;
            case "2000004660741690":
                return 7L;
            case "2000004660741711":
                return 8L;
            case "2000004660741694":
                return 9L;
            case "2000004660741719":
                return 10L;
            case "2000004660741698":
                return 11L;
            case "2000004662545729":
                return 12L;
            default:
               return  -1L;
        }
    }

    private static Map<Long, List<HomeworkDto>> buildMap(){
        Map<Long, List<HomeworkDto>> map = new HashMap<>();

        List<HomeworkDto> a1 = new ArrayList<>();
        map.put(2000004660741715L, a1);

        HomeworkDto dto2 = new HomeworkDto();
        dto2.setId(1032875L);
        dto2.setTopicId(2000004660741698L);
        dto2.setTitle("拓展课-2作业");
        List<HomeworkDto> a2 = new ArrayList<>();
        a2.add(dto2);
        map.put(2000004660741698L, a2);


        HomeworkDto dto3 = new HomeworkDto();
        dto3.setId(1031448L);
        dto3.setTopicId(2000004660741730L);
        dto3.setTitle("【重要！！】");
        List<HomeworkDto> a3 = new ArrayList<>();
        a3.add(dto3);
        map.put(2000004660741730L, a3);


        HomeworkDto dto4 = new HomeworkDto();
        dto4.setId(1032847L);
        dto4.setTopicId(2000004660741719L);
        dto4.setTitle("拓展课-1作业");
        List<HomeworkDto> a4 = new ArrayList<>();
        a4.add(dto4);
        map.put(2000004660741719L, a4);


        List<HomeworkDto> a5 = new ArrayList<>();
        map.put(2000004660741702L, a5);


        HomeworkDto dto6 = new HomeworkDto();
        dto6.setId(1031447L);
        dto6.setTopicId(2000004660741723L);
        dto6.setTitle("第1课作业");
        List<HomeworkDto> a6 = new ArrayList<>();
        a6.add(dto6);
        map.put(2000004660741723L, a6);

        HomeworkDto dto7 = new HomeworkDto();
        dto7.setId(1031442L);
        dto7.setTopicId(2000004660741690L);
        dto7.setTitle("第3课作业");
        List<HomeworkDto> a7 = new ArrayList<>();
        a7.add(dto7);
        map.put(2000004660741690L, a7);


        HomeworkDto dto8 = new HomeworkDto();
        dto8.setId(1031444L);
        dto8.setTopicId(2000004660741706L);
        dto8.setTitle("第2课作业");
        List<HomeworkDto> a8 = new ArrayList<>();
        a8.add(dto8);
        map.put(2000004660741706L, a8);

        HomeworkDto dto9 = new HomeworkDto();
        dto9.setId(1032873L);
        dto9.setTopicId(2000004660741711L);
        dto9.setTitle("第4课作业");
        List<HomeworkDto> a9 = new ArrayList<>();
        a9.add(dto9);
        map.put(2000004660741711L, a9);

        List<HomeworkDto> a10 = new ArrayList<>();
        map.put(2000004660741727L, a10);

        List<HomeworkDto> a11 = new ArrayList<>();
        map.put(2000004660741694L, a11);

        List<HomeworkDto> a12 = new ArrayList<>();
        map.put(2000004662545729L, a12);

        return map;
    }

}
