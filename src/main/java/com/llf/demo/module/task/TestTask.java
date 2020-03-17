package com.llf.demo.module.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: Oliver.li
 * @Description: Spring定时器， @Async表示并行执行
 * @date: 2018/8/6 17:44
 */
@Component
public class TestTask {

    private final static Logger logger = LoggerFactory.getLogger(TestTask.class);

    @Scheduled(cron = "0 0/2 * * * ?")
    public void task1() {
        logger.info(Thread.currentThread().getName() + " | task1 ");
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    @Async
    public void task2(){
        logger.info(Thread.currentThread().getName() + " | task2 ");
    }



}
