package com.llf.demo.java.proxy;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/3/2 16:52
 */
public class CharlieServiceImpl implements AlphaService, BetaService {
    @Override
    public void addAlpha() {
        System.out.println("addAlpha...");
    }

    @Override
    public String getAlpha() {
        System.out.println("getAlpha...");
        return "getAlpha";
    }

    @Override
    public void addBeta() {
        System.out.println("addBeta...");
    }

    @Override
    public String getBeta() {
        System.out.println("getBeta...");
        return "getBeta";
    }
}
