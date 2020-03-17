package com.llf.demo.java.enumeration;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/3/17 10:52
 */
public class CalculatorText {


    public enum Calculator{

        /**
         * 加法
         */
        ADD{
            @Override
            public double execute(double x, double y){
                return x + y;
            }
        },
        SUB{
            @Override
            public double execute(double x, double y){
                return x - y;
            }
        },
        MUL{
            @Override
            public double execute(double x, double y){
                return x * y;
            }
        },
        DIV{
            @Override
            public double execute(double x, double y){
                return x / y;
            }
        };

        public abstract double execute(double x, double y);
    }

    public static void main(String[] args) {

        System.out.println(Calculator.ADD.execute(1, 1));

    }

}
