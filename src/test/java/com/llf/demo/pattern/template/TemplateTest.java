package com.llf.demo.pattern.template;

/**
 * @author: Oliver.li
 * @Description: 模板方法模式
 * @date: 2020/4/27 11:16
 */
public class TemplateTest {

    public static void main(String[] args) {
        AbstractTemplate t1 = new ConcreteTemplate("AA");
        t1.templateMethod();

        AbstractTemplate t2 = new ConcreteTemplate("BB");
        t2.templateMethod();
    }



}
