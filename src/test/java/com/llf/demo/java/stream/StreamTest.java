package com.llf.demo.java.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/3/28 11:32
 */
public class StreamTest {


    @Test
    public void testMap(){
        List<Integer> list = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
        }};

        List<String> collect = list.stream().map(i -> {

            if (i > 3) {
                return i + "";
            } else {
                return null;
            }

        }).filter(Objects::nonNull).collect(Collectors.toList());

        System.out.println(collect);
    }

}
