//package com.llf.demo.module.redis.service;
//
//import com.llf.demo.common.SpringContext;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//
///**
// * @author: Oliver.li
// * @Description: RedissonLock
// * @date: 2018/12/3 11:22
// */
//public class RedissonLock {
//
//    private RedissonClient redisson;
//
//    {
//        redisson = SpringContext.getBean("redisson", RedissonClient.class);
//    }
//
//    private String lockKey;
//
//    private RLock lock;
//
//    public RedissonLock(String lockKey) {
//        this.lockKey = lockKey;
//        lock = redisson.getLock(lockKey);
//    }
//
//    /**
//     * 获取锁
//     * @return
//     */
//    public void lock(){
//        lock.lock();
//    }
//
//    public void unlock(){
//        lock.unlock();
//    }
//}
