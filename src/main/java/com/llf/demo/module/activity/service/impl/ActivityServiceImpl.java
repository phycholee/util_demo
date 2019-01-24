package com.llf.demo.module.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llf.demo.common.PageParam;
import com.llf.demo.module.activity.mapper.ActivityMapper;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import com.llf.demo.module.redis.service.RedisLock;
import com.llf.demo.module.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    @Autowired
    private RedisService redisService;

    @Override
    @Cacheable(value = "activity", key = "#id")
    public Activity get(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = "activity", key = "#activity.id")
    public Activity save(Activity activity) {
        int result = activityMapper.insertSelective(activity);
        if (result > 0) {
            return activity;
        }
        return null;
    }

    @Override
    @CacheEvict(value = "activity", key = "#activity.id")
    public Activity update(Activity activity) {
        int result = activityMapper.updateByPrimaryKeySelective(activity);
        if (result > 0) {
            return activity;
        }
        return null;
    }

    @Override
    @CacheEvict(value = "activity", key = "#id")
    public int delete(Integer id) {
        return activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Cacheable("activitys")
    public PageInfo<Activity> page(PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());

        List<Activity> list = activityMapper.selectByExample(null);

        return new PageInfo<>(list);
    }

    @Override
    public Activity lockGet(Integer id) {
        String key = String.format("ACTIVITY_LOCK_%s", id);

        RedisLock lock = new RedisLock(key);

        lock.lock();
        try {
            Thread.sleep(10000);
            return activityMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }
}
