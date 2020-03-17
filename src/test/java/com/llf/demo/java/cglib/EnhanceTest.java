package com.llf.demo.java.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/8/19 11:34
 */
public class EnhanceTest {

    public static void main(String[] args) {

        Callback[] callbacks = new Callback[]{
                new MethodInterceptorImpl(new MyClass())
        };

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyClass.class);
        enhancer.setCallbacks(callbacks);

        MyClass service = (MyClass) enhancer.create();
        service.method1();

    }

    private static class MethodInterceptorImpl implements MethodInterceptor {

        private MyClass myClass;

        public MethodInterceptorImpl(MyClass myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("Before invoke");
            Object result = proxy.invoke(myClass, args);
            System.out.println("After invoke");
            return result;
        }
    }
}

class MyClass{

    public void method1(){
        System.out.println("EnhanceService.method1()");
        method2();
    }

    public void method2(){
        System.out.println("EnhanceService.method2()");
    }

}