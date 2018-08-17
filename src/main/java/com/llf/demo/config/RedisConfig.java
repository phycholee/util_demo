package com.llf.demo.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;

import java.time.Duration;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/6/26 10:21
 */
@Configuration
public class RedisConfig {

    /**
     * 设置redisTemplate序列化方式
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(objectMapper);

        com.alibaba.fastjson.support.spring.FastJsonRedisSerializer<Object> fastJsonRedisSerializer =
                new com.alibaba.fastjson.support.spring.FastJsonRedisSerializer<>(Object.class);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 设置 @Cacheable 序列化方式
     * 设置过期时间为1天
     * @return
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<>(Object.class);
        ParserConfig parserConfig = ParserConfig.getGlobalInstance();
        parserConfig.addAccept("com.llf.demo.");
        parserConfig.addAccept("com.github.pagehelper.");

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer)).entryTtl(Duration.ofDays(1));
        return configuration;
    }

    /**
     * 分布式限流lua脚本
     * @return
     */
    @Bean
    public DefaultRedisScript<Number> rateLimitLua(){
        DefaultRedisScript<Number> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("rateLimit.lua")));
        redisScript.setResultType(Number.class);
        return redisScript;
    }
}
