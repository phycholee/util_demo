package com.llf.demo.pattern.decorator;

/**
 * @author: Oliver.li
 * @Description: 装饰模式测试
 *
 * 装饰模式是为已有功能动态的添加更多功能的一种方式。他把每个要装饰的功能放在单独的类中，并让这个类包装它所要装饰的对象。有效的把类的核心职责和装饰功能区分开了。
 *
 * @date: 2020/3/20 14:49
 */
public class DecoratorTest {

    public static void main(String[] args) {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();

        d1.setComponent(c);
        d2.setComponent(d1);
        d2.operation();
    }

}
