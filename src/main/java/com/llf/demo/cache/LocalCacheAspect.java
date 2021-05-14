package com.llf.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: Oliver.li
 * @Description: 本地缓存
 * @date: 2021/5/6 18:21
 */
@Aspect
@Component
public class LocalCacheAspect {

    private static final ConcurrentHashMap<String, Cache<String, Object>> CACHE_MAP = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(LocalCacheAspect.class);

    @Around("@annotation(com.llf.demo.cache.LocalCache)")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LocalCache localCache = method.getAnnotation(LocalCache.class);

        String cacheKey = method.getDeclaringClass().getName()  + "_" + method.getName();

        if (localCache != null){

            Cache<String, Object> cache = getCache(cacheKey, localCache.maximumSize(), localCache.expireSeconds());
            if (cache == null){
                return joinPoint.proceed();
            }

            //使用注解中的key, 支持SpEL表达式
            String spEL = localCache.key();
            String key = generateKeyBySpEL(spEL, joinPoint);

            Object present = cache.getIfPresent(key);
            if (present != null){
                return present;
            }

            Object proceed = joinPoint.proceed();
            if (proceed != null){
                cache.put(key, proceed);
            }
            return proceed;
        } else {
            return joinPoint.proceed();
        }
    }

    private Cache<String, Object> getCache(String cacheKey, int maximumSize, int expireSeconds){

        Cache<String, Object> cache = CACHE_MAP.get(cacheKey);
        if (cache == null){
            cache = initCache(cacheKey, maximumSize, expireSeconds);

            //不存在才放入
            if (!CACHE_MAP.contains(cacheKey)){
                CACHE_MAP.put(cacheKey, cache);
            }
            cache = CACHE_MAP.get(cacheKey);
        }
        return cache;
    }

    private Cache<String, Object> initCache(String cacheKey, int maximumSize, int expireSeconds){
        synchronized (cacheKey){
            return CacheBuilder.newBuilder().maximumSize(maximumSize).expireAfterWrite(expireSeconds, TimeUnit.SECONDS).build();
        }
    }

    /**
     * 用于SpEL表达式解析.
     */
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private static final DefaultParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * SpEL表达式缓存Key生成器.
     * 注解中传入key参数，则使用此生成器生成缓存.
     *
     * @param spELString
     * @param joinPoint
     * @return
     */
    private String generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = NAME_DISCOVERER.getParameterNames(methodSignature.getMethod());
        Expression expression = PARSER.parseExpression(spELString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for(int i = 0 ; i < args.length ; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return expression.getValue(context).toString();
    }
}
