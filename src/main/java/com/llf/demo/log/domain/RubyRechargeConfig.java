package com.llf.demo.log.domain;

import lombok.Data;

@Data
public class RubyRechargeConfig {

    /**
     * 对应营收的充值配置ID，注意，测试环境和线上的充值配置ID可能不一致，不能根据该ID做映射
     */
    private int id;

    /**
     * 充值项名称
     */
    private String name;

    /**
     * 业务APPID
     */
    private int appid;

    /**
     * 充值渠道
     */
    private int usedChannelType;

    /**
     * 充值到账货币类型
     */
    private int destCurrencyType;

    private int chargeRate;

    private int offersType;

    private int offersRate;

    private boolean offersCurrencySame;

    private String offersCurrencyType;

    /**
     * 支付金额，单位人民币分
     */
    private int srcAmount;

    /**
     * 到账货币数量
     */
    private int destAmount;

    /**
     * 充值项生效时间
     */
    private long effectStartTime;

    /**
     * 充值项失效时间
     */
    private long effectEndTime;

    private int status;

    private int weight;

    private String productId;

    private String expand;

}
