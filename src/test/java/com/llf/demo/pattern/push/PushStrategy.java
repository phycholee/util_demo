package com.llf.demo.pattern.push;

/**
 * @author: Oliver.li
 * @Description: 推送策略
 * @date: 2020/4/10 15:03
 */
public interface PushStrategy {

    /**
     *

     redis数据保存结构设计

     queue_[pushId]  //准备发送队列

     pushId_set_[strategyId]     //策略id下的pushId

     preparing_set    //准备中set

     ready_set		//准备完毕set

     准备数据步骤
     1. 根据id分片，单机只处理一个策略id
     2. 策略id塞进preparing_set，策略id在preparing_set或ready_set中有数据不处理
     3. 查询数据，将准发送数据塞进queue_[pushId]，一个pushId一个队列
     4. preparing_set删除策略id，ready_set数据添加策略id


     发送数据步骤
     1. 从ready_set中获取策略id，分片处理
     2. 从pushId_set_[strategyId]获取pushId集合，从queue_[pushId]消费，发送
     3. ready_set删除策略id
     */

    /**
     * 准备数据
     */
    void prepare();

    /**
     * 发送数据
     */
    void send();

}
