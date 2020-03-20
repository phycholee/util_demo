package com.llf.demo.pattern.strategy;

/**
 * @author: Oliver.li
 * @Description: 策略模式测试
 *
 * 策略模式定义了一系列算法的方法，所有的算法完成的工作相同，只是实现不同，他可以用相同的方式调用所有算法，减少了各种算法类与使用方法类之间的耦合。
 *
 *
 * @date: 2020/3/20 15:18
 */
public class StrategyTest {


    public static void main(String[] args) {

        Context c1 = new Context(new ConcreteStrategyA());
        c1.strategy();

        Context c2 = new Context(new ConcreteStrategyB());
        c2.strategy();

        Context c3 = new Context(new ConcreteStrategyC());
        c3.strategy();

    }

}
