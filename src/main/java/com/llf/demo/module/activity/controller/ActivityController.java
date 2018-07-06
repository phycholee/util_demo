package com.llf.demo.module.activity.controller;

import com.github.pagehelper.PageInfo;
import com.llf.demo.common.JsonData;
import com.llf.demo.common.PageParam;
import com.llf.demo.common.PagerJsonData;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public JsonData save(@RequestBody Activity activity){

        activityService.save(activity);

        return JsonData.success(activity);
    }

    @PutMapping("")
    public JsonData update(@RequestBody Activity activity){
        if (activity.getId() == null){
            return JsonData.fail("参数错误", null);
        }

        activityService.update(activity);

        return JsonData.success(activity);
    }

    @DeleteMapping("/{id}")
    public JsonData delete(@PathVariable Integer id){

        activityService.delete(id);
        return JsonData.success();
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
