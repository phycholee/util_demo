package com.llf.demo.pattern.decorator;

/**
 * @author: Oliver.li
 * @Description: 具体装饰对象A
 * @date: 2020/3/20 14:46
 */
public class ConcreteDecoratorA extends Decorator {

    private String addedState;

    @Override
    public void operation() {
        super.operation();
        addedState = "New State";
        System.out.println("ConcreteDecoratorA.operation doing something");
    }
}
