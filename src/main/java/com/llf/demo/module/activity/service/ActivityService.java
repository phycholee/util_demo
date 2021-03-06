package com.llf.demo.module.activity.service;

import com.github.pagehelper.PageInfo;
import com.llf.demo.common.PageParam;
import com.llf.demo.module.activity.model.Activity;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/6/4 18:30
 */
public interface ActivityService {

    Activity get(Integer id);

    Activity save(Activity activity);

    Activity update(Activity activity);

    int delete(Integer id);

    PageInfo<Activity> page(PageParam pageParam);

    Activity lockGet(Integer id);
}
