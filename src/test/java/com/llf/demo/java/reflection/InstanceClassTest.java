package com.llf.demo.java.reflection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/7/10 11:28
 */
public class InstanceClassTest {


    public static void main(String[] args) {

        List<Number> numbers = new ArrayList<>();

        numbers.add(new Integer(1));
        numbers.add(new Double(1.1));
        numbers.add(new BigDecimal("2.1"));

        for (Number number : numbers) {

            Class<? extends Number> aClass = number.getClass();

            System.out.println(aClass);
        }

    }

}
