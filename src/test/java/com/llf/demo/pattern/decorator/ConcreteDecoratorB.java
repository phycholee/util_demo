package com.llf.demo.pattern.decorator;

/**
 * @author: Oliver.li
 * @Description: 具体装饰对象B
 * @date: 2020/3/20 14:46
 */
public class ConcreteDecoratorB extends Decorator {

    @Override
    public void operation() {
        super.operation();
        addedBehavior();
        System.out.println("ConcreteDecoratorB.operation doing something");
    }

    private void addedBehavior(){
        System.out.println("ConcreteDecoratorB.addedBehavior doing something");
    }
}
