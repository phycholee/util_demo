package com.llf.demo.module.redis.service;

import com.llf.demo.common.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.Assert;
import java.util.Random;
import java.util.UUID;

/**
 * @author: Oliver.li
 * @Description: Redis分布式锁
 * @date: 2018/6/26 11:17
 */
public class RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    /**
     * 默认请求锁的超时时间(ms 毫秒)
     */
    private static final long WAIT_TIME_MILLISECOND = 1000;

    /**
     * 默认锁的有效时间(s)
     */
    public static final int EXPIRE_TIME_SECOND = 10;

    /**
     * 解锁的lua脚本
     */
    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    private RedisTemplate redisTemplate;

    {
        redisTemplate = SpringContext.getBean("redisTemplate", RedisTemplate.class);
    }


    private String lockKey;

    private String lockValue;

    /**
     * 锁的有效时间(s)
     */
    private int expireTime = EXPIRE_TIME_SECOND;

    /**
     * 请求锁的超时时间(ms)
     */
    private long timeOut = WAIT_TIME_MILLISECOND;

    /**
     * 锁标记
     */
    private volatile boolean locked = false;

    private final Random random = new Random();

    /**
     * 使用默认的锁过期时间和请求锁的超时时间
     * @param lockKey
     */
    public RedisLock(String lockKey) {
        Assert.notNull(lockKey, "lockKey can not be null");
        this.lockKey = lockKey;
        this.setLockValue();
    }

    /**
     * 使用默认的请求锁的超时时间，指定锁的过期时间
     * @param lockKey 锁名称
     * @param expireTime 锁过期分钟
     */
    public RedisLock(String lockKey, int expireTime) {
        this(lockKey);
        this.expireTime = expireTime;
    }

    /**
     *使用默认的锁的过期时间，指定请求锁的超时时间
     * @param lockKey 锁名称
     * @param timeOut 请求锁超时毫秒
     */
    public RedisLock(String lockKey, long timeOut) {
        this(lockKey);
        this.timeOut = timeOut;
    }

    /**
     *指定锁的过期时间和请求锁的超时时间
     * @param lockKey 锁名称
     * @param expireTime 锁过期分钟
     * @param timeOut 请求锁超时毫秒
     */
    public RedisLock(String lockKey, int expireTime, long timeOut) {
        this(lockKey);
        this.expireTime = expireTime;
        this.timeOut = timeOut;
    }

    /**
     * 尝试获取锁，超时返回失败
     * @return
     */
    public boolean tryLock() {
        long timeout = timeOut * 1000000;

        long nowtime = System.nanoTime();

        while ((System.nanoTime() - nowtime) < timeout){
            if (this.set(lockKey, lockValue, expireTime)){
                locked = true;
                return locked;
            }

            //睡眠一段时间
            this.sleep(100, 5000000);
        }

        return locked;
    }

    /**
     * 获取锁，失败直接返回
     * @return
     */
    public boolean lock() {
        locked = this.set(lockKey, lockValue, expireTime);
        return locked;
    }

    /**
     * 阻塞获取锁
     * @return
     */
    public boolean blockingLock() {
        while (true){
            if (this.set(lockKey, lockValue, expireTime)){
                locked = true;
                return locked;
            }

            //睡眠一段时间
            this.sleep(10, 50000);
        }

    }

    /**
     * 解锁
     * @return
     */
    public boolean unlock(){
        if (locked){
            Boolean result =  redisTemplate.getConnectionFactory().getConnection().eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(), lockValue.getBytes());

            if (result){
                logger.info("Redis分布式锁，解锁{}成功", lockKey);
            } else {
                logger.info("Redis分布式锁，解锁{}失败", lockKey);
            }

            locked = !result;
            return result;
        }

        return false;
    }

    /**
     * redis使用nx设值
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    private Boolean set(String key, String value, long expireTime){
        Boolean result = redisTemplate.getConnectionFactory().getConnection().set(key.getBytes(), value.getBytes(), Expiration.seconds(expireTime), RedisStringCommands.SetOption.SET_IF_ABSENT);

        if (result){
            logger.info("Redis分布式锁，获取锁{}成功", lockKey);
        } else {
            logger.info("Redis分布式锁，获取锁{}失败", lockKey);
        }

        return result;
    }

    /**
     * 睡眠
     * @param millis
     * @param nanos
     */
    private void sleep(long millis, int nanos) {
        try {
            Thread.sleep(millis, random.nextInt(nanos));
        } catch (InterruptedException e) {
            logger.info("获取分布式锁休眠被中断：", e);
        }
    }

    /**
     * 设置随机锁值
     */
    private void setLockValue(){
        lockValue =  UUID.randomUUID().toString().replaceAll("-", "");
    }
}
