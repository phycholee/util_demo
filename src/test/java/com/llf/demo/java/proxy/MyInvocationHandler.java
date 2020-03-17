package com.llf.demo.java.proxy;

import java.lang.reflect.Method;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/3/2 16:47
 */
public interface MyInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Exception;

}
