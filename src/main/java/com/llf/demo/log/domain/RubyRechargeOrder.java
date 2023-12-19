package com.llf.demo.log.domain;

import lombok.Data;

@Data
public class RubyRechargeOrder {

    /**
     * 订单唯一标识ID
     */
    private long id;

    /**
     * 用户uid
     */
    private long uid;

    /**
     * 频道sid
     */
    private int sid;

    /**
     * 下单毫秒级时间戳
     */
    private long createTime;

    private String finishTime;

    private int status;

    /**
     * 人民币金额，单位：元
     */
    private double amount;

    /**
     * 充值货币类型
     */
    private int currencyType;

    /**
     * 支付渠道
     */
    private String payChannel;

    /**
     * 支付方式
     */
    private String payMethod;

    private String bankId;

    /**
     * product id,  eg：com.findyou.level12
     */
    private String prodId;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 商品描述
     */
    private String prodDesc;

    /**
     * 用户IP
     */
    private String userIp;

    /**
     * 外部订单号
     */
    private String externOrderId;

    private String chOrderId;

    private String chDealTime;

    /**
     * 业务APPID
     */
    private int appid;

    /**
     * 充值渠道：10001=iOS；10002=Android；10000=Web；10004=微信公众号
     */
    private int channelType;

    private String expand;

    private String actionId;

    private String productId;

    /**
     * 对应营收的充值配置ID
     */
    private int chargeConfigId;

}
