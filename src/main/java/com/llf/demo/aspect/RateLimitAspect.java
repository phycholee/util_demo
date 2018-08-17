package com.llf.demo.aspect;

import com.llf.demo.common.annotation.RateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author: Oliver.li
 * @Description: 分布式限流拦截
 * @date: 2018/8/17 16:27
 */
@Aspect
@Component
public class RateLimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitAspect.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript<Number> rateLimitLua;

    @Around("execution(* com.llf.demo.module ..*(..) )")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RateLimit rateLimit = method.getAnnotation(RateLimit.class);

        if (rateLimit != null){
            Class<?> clazz = method.getDeclaringClass();
            String key = clazz.getName() + "::" + method.getName() + "::" + rateLimit.key();

            List<String> keys = Collections.singletonList(key);

            Number result = (Number) redisTemplate.execute(rateLimitLua, keys, rateLimit.count(), rateLimit.second());

            if (result != null && result.intValue() != 0 && result.intValue() <= rateLimit.count()){
                logger.info("限流时间段内访问第：{} 次", result.toString());
                return joinPoint.proceed();
            }

            logger.info("超过限流次数");
            throw new RuntimeException("请稍后重试");
        } else {
            return joinPoint.proceed();
        }
    }

}
