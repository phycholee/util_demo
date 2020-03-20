package com.llf.demo.pattern.strategy;

/**
 * @author: Oliver.li
 * @Description: 上下文
 * @date: 2020/3/20 15:16
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategy(){
        strategy.algorithm();
    }
}
