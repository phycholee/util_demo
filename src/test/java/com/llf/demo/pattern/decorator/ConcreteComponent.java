package com.llf.demo.pattern.decorator;

/**
 * @author: Oliver.li
 * @Description: 具体的对象
 * @date: 2020/3/20 14:42
 */
public class ConcreteComponent implements Component {


    @Override
    public void operation() {
        System.out.println("ConcreteComponent.operation doing something");
    }
}
