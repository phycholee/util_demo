package com.llf.demo.pattern.strategy;

/**
 * @author: Oliver.li
 * @Description: 具体算法实现A
 * @date: 2020/3/20 15:13
 */
public class ConcreteStrategyA implements Strategy {

    @Override
    public void algorithm() {
        System.out.println("ConcreteStrategyA.algorithm");
    }
}
