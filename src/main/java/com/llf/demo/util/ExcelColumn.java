package com.llf.demo.util;

import java.lang.annotation.*;

/**
 * @author: Oliver.li
 * @Description: Excel列属性注解
 * @date: 2018/5/2 11:25
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ExcelColumn {
    String name();
    String dateFormat() default "yyyy-MM-dd HH:mm:ss";
    int width() default 20;
}
