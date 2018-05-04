package com.llf.demo.controller.dto;

import com.llf.demo.util.ExcelColumn;

import java.util.Date;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/28 11:31
 */
public class ActivityRespDto {

    @ExcelColumn(name = "活动Id", width = 15)
    private String activityId;

    @ExcelColumn(name = "活动名称")
    private String activityName;

    @ExcelColumn(name = "活动类型")
    private String activityType;

    private int stock;

    @ExcelColumn(name = "开始时间", dateFormat = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @ExcelColumn(name = "结束时间", dateFormat = "yyyy-MM-dd HH:mm")
    private Date endTime;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ActivityRespDto{" +
                "activityId='" + activityId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", activityType='" + activityType + '\'' +
                ", stock=" + stock +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
