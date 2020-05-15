package com.llf.demo.pattern.template;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/4/27 11:15
 */
public class ConcreteTemplate extends AbstractTemplate {

    private String name;

    public ConcreteTemplate(String name) {
        this.name = name;
    }

    @Override
    public void operation1() {
        System.out.println("ConcreteTemplate.operation1, name=" + name);
    }

    @Override
    public void operation2() {
        System.out.println("ConcreteTemplate.operation2, name=" + name);
    }
}
