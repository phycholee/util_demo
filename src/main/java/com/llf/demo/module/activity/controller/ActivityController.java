package com.llf.demo.module.activity.controller;

import com.github.pagehelper.PageInfo;
import com.llf.demo.common.JsonData;
import com.llf.demo.common.PageParam;
import com.llf.demo.common.PagerJsonData;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/6/4 18:39
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/{id}")
    public JsonData get(@PathVariable Integer id){

        Activity activity = activityService.get(id);

        return JsonData.success(activity);
    }

    @GetMapping("/list")
    public JsonData list(PageParam pageParam){

        PageInfo<Activity> page = activityService.page(pageParam);

        return PagerJsonData.page(page.getPageNum(), page.getPageSize(), page.getTotal(), page.getList());
    }

    @GetMapping("lockGet/{id}")
    public JsonData lockGet(@PathVariable Integer id){

        Activity activity = activityService.lockGet(id);

        return JsonData.success(activity);
    }
}
