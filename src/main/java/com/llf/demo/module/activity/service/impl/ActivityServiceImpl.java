package com.llf.demo.module.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llf.demo.common.PageParam;
import com.llf.demo.module.activity.mapper.ActivityMapper;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/6/4 18:31
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public Activity get(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Activity> page(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());

        List<Activity> list = activityMapper.selectByExample(null);

        return new PageInfo<>(list);
    }
}
