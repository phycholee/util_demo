package com.llf.demo.java.invokedynamic;

import java.lang.reflect.Method;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/9/4 23:37
 */
public class Test {


    class GrandFather{

        void thinking(){
            System.out.println("I am grandFather");
        }

    }

    class Father extends GrandFather{

        @Override
        void thinking() {
            System.out.println("I am father");
        }
    }

    class Son extends Father{

        @Override
        void thinking() {
//            try {
//                MethodType type = MethodType.methodType(void.class);
//                MethodHandle handle = lookup().findSpecial(GrandFather.class, "thinking", type, getClass());
//                handle.invoke(this);
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }

            try {
                Method thinking = GrandFather.class.getDeclaredMethod("thinking");
                thinking.setAccessible(true);
                thinking.invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        Son son = new Test().new Son();
//        son.thinking();
        (new Test().new Son()).thinking();
    }
}
