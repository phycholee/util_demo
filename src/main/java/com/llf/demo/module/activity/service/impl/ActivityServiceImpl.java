package com.llf.demo.module.activity.service.impl;

import com.llf.demo.module.activity.mapper.ActivityMapper;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
