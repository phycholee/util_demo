package com.llf.demo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/12/20 17:42
 */
public class ThreadPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    public static void main(String[] args) {

        ThreadPoolExecutor executor = getExecutor();

        for (int i = 0; i < 5; i++){

            final int ii = i;
            executor.execute(()-> {

                while (true) {
                    logger.info("aa" + ii);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
        }
    }


    /**
     * 初始化线程池
     */
    private static ThreadPoolExecutor getExecutor(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("gala2019-add-score-pool-%d").build();
        return new ThreadPoolExecutor(5, 5,
                60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
}
