package com.llf.demo.module.redis.service;

import com.llf.demo.util.ExcelUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/5/7 11:18
 */
public class ExcelUtilTest {

    @Test
    public void read() throws Exception {

        String[] fields = {"activityId", "activityName", "activityType", "startTime", "endTime"};

        File file = new File("E:\\下载3.xls");

        FileInputStream is = new FileInputStream(file);

        List<Map<String, Object>> list = ExcelUtil.read(is, fields);

        System.out.println(list);

    }

}
