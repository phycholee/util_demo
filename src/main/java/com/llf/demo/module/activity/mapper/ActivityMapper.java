package com.llf.demo.module.activity.mapper;

import com.llf.demo.module.activity.model.Activity;
import com.llf.demo.module.activity.model.ActivityCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
    long countByExample(ActivityCriteria example);

    int deleteByExample(ActivityCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityCriteria example);

    Activity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityCriteria example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityCriteria example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
}