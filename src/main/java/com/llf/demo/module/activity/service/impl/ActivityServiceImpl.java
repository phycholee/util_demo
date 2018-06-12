package com.llf.demo.module.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llf.demo.common.PageParam;
import com.llf.demo.module.activity.mapper.ActivityMapper;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("activity")
    public Activity get(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    @CachePut("activity")
    public int save(Activity activity) {
        return activityMapper.insertSelective(activity);
    }

    @Override
    @CachePut("activity")
    public int update(Activity activity) {
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    @Override
    @CacheEvict("activity")
    public int delete(Integer id) {
        return activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Activity> page(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());

        List<Activity> list = activityMapper.selectByExample(null);

        return new PageInfo<>(list);
    }
}
