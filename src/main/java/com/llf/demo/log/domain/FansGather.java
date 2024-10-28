package com.llf.demo.log.domain;

import lombok.Data;

import java.util.Date;

@Data
public class FansGather {
    private Long id;

    /**
     * 主播uid
     */
    private Long auid;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 红宝石流转官方号
     */
    private Long officialUid;

    /**
     * 红宝石数量
     */
    private Integer rubyAmount;

    /**
     * 追加奖励类型，0-不追加，1-活动红宝石
     */
    private Integer rewardType;

    /**
     * 结算状态，0-未结算，1-已结算
     */
    private Integer settleStatus;

    /**
     * 任务完成状态，0-未完成，1-已完成
     */
    private Integer taskStatus;

    /**
     * 开启召集前已经完成的任务id，逗号分隔
     */
    private String finishedTaskIds;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
