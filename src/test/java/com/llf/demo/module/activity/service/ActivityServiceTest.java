package com.llf.demo.module.activity.service;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/7/3 15:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 3, threads = 2)
    public void get() {
        activityService.get(1);
    }
}