package com.llf.demo.log.domain;

import lombok.Data;

@Data
public class RubyRechargeWaterFlow {

    /**
     * 充值配置项
     */
    private RubyRechargeConfig config;

    /**
     * 红宝石充值订单
     */
    private RubyRechargeOrder order;

}
