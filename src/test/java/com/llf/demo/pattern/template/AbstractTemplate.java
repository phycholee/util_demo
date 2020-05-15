package com.llf.demo.pattern.template;

/**
 * @author: Oliver.li
 * @Description: 抽象模板
 * @date: 2020/4/27 11:13
 */
public abstract class AbstractTemplate {

    public abstract void operation1();
    public abstract void operation2();

    public void templateMethod(){
        operation1();
        operation2();
        System.out.println("done");
    }

}
