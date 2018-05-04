package com.llf.demo.controller;

import com.llf.demo.controller.dto.ActivityRespDto;
import com.llf.demo.util.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/28 11:24
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);


    @GetMapping("/export")
    public void exportData(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<ActivityRespDto> list = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            ActivityRespDto dto = new ActivityRespDto();
            dto.setActivityId(i + "");
            dto.setActivityName("惊喜" + i);
            dto.setActivityType("LUCK");
            dto.setStartTime(new Date());
            dto.setEndTime(new Date());

            list.add(dto);
        }

        String[] titles = {"活动Id", "活动名称", "活动类型", "开始时间", "结束时间"};

        long startTime = System.currentTimeMillis();
        ExcelUtil.export(list, "下载", titles, response, ActivityRespDto.class);
        long endTime = System.currentTimeMillis();

        logger.info("export spend time: " + (endTime - startTime));
    }

    @GetMapping("/export2")
    public void exportData2(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("activityId", i);
            map.put("activityName", "惊喜" + i);
            map.put("activityType", "LUCK");
            map.put("startTime", new Date());
            map.put("endTime", new Date());

            list.add(map);
        }

        String[] fields = {"activityId", "activityName", "activityType", "startTime", "endTime"};
        String[] titles = {"活动Id", "活动名称", "活动类型", "开始时间", "结束时间"};

        long startTime = System.currentTimeMillis();
        ExcelUtil.export(list, "下载2", titles, fields, response);
        long endTime = System.currentTimeMillis();

        logger.info("export2 spend time: " + (endTime - startTime));
    }

    @GetMapping("/export3")
    public void exportData3(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<ActivityRespDto> list = new ArrayList<>();

        for (int i = 1; i <= 10000; i++) {
            ActivityRespDto dto = new ActivityRespDto();
            dto.setActivityId(i + "");
            dto.setActivityName("惊喜哈哈" + i);
            dto.setActivityType("LUCK");
            dto.setStartTime(new Date());
            dto.setEndTime(new Date());

            list.add(dto);
        }

        long startTime = System.currentTimeMillis();
        ExcelUtil.export(list, "下载3", response, ActivityRespDto.class);
        long endTime = System.currentTimeMillis();

        logger.info("export3 spend time: " + (endTime - startTime));
    }

}
