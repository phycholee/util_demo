package com.llf.demo.util;

import com.llf.demo.module.example.dto.ActivityRespDto;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @author: Oliver.li
 * @Description: 反射工具类
 * @date: 2018/8/16 15:06
 */
public class ReflectionUtil {

    private static final String GETTER_PREFIX = "get";


    public static Object invokeGetter(Object obj, String fieldName){
        String methodGetterName = GETTER_PREFIX + StringUtils.capitalize(fieldName);
        return InvokeMethod(obj, methodGetterName, new Class[]{}, new Object[]{});
    }

    public static Object InvokeMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes, final Object[] args){
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null){
            throw new IllegalArgumentException("Cannot find method [" + methodName + "] on target [" + obj + "]");
        }

        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    public static Method getAccessibleMethod(final Object obj, final String methodName, final Class<?>[] parameterTypes){

        Assert.notNull(obj, "object cannot be null");
        Assert.notNull(obj, "methodName cannot be null");

        for (Class<?> searchType = obj.getClass(); searchType != Object.class; searchType = searchType.getSuperclass()){
            try {
                Method method = searchType.getDeclaredMethod(methodName, parameterTypes);
                makeAccessible(method);
                return method;
            } catch (NoSuchMethodException e) {
                //找不到向父类找
                continue;
            }
        }

        return null;
    }

    private static void makeAccessible(Method method) {
        if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
                && !method.isAccessible()){
            method.setAccessible(true);
        }
    }


    /**
     * 将反射时的checked exception转换为unchecked exception.
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

    public static void main(String[] args) {

        ActivityRespDto dto = new ActivityRespDto();
        dto.setActivityId("1");
        dto.setActivityName("惊喜");
        dto.setActivityType("LUCK");
        dto.setStartTime(new Date());
        dto.setEndTime(new Date());

        Object activityName = invokeGetter(dto, "activityType");
        System.out.println(activityName);

    }
}
