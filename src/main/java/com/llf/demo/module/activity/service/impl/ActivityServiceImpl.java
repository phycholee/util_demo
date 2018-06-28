package com.llf.demo.module.activity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llf.demo.common.PageParam;
import com.llf.demo.module.activity.mapper.ActivityMapper;
import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.service.ActivityService;
import com.llf.demo.module.redis.service.RedisLock;
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

    @Override
    public Activity lockGet(Integer id) {
        String key = String.format("ACTIVITY_LOCK_%s", id);

        RedisLock lock = new RedisLock(key);

        try {
            if (lock.lock()){
                return activityMapper.selectByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }
}
