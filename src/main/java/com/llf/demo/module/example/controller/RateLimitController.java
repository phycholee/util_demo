package com.llf.demo.module.example.controller;

import com.llf.demo.common.JsonData;
import com.llf.demo.common.annotation.RateLimit;
import com.llf.demo.module.example.dto.ActivityRespDto;
import com.llf.demo.util.IpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Oliver.li
 * @date 2023/6/13 16:47
 */
@RestController
@RequestMapping("/rate")
public class RateLimitController {

    private static Logger logger = LoggerFactory.getLogger(RateLimitController.class);


    @RateLimit(key = "test", second = 10, count = 3)
    @GetMapping("/activity")
    public JsonData getActivity(HttpServletRequest request){
        ActivityRespDto dto = new ActivityRespDto();
        dto.setActivityId("1");
        dto.setActivityName("我是谁");
        dto.setActivityType("WHO");
        dto.setStartTime(new Date());
        dto.setEndTime(new Date());

        logger.info("IP: " + IpUtil.getIpAddr(request));

        return JsonData.success(dto);
    }

}
