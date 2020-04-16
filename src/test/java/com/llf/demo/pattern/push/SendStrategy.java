package com.llf.demo.pattern.push;

/**
 * @author: Oliver.li
 * @Description: 发送策略
 * @date: 2020/4/10 16:42
 */
public interface SendStrategy {

    /**
     * 1.获取数据
     * @return
     */
    String pop();

    /**
     * 2.过滤数据
     */
    void filter();

    /**
     * 3.发送
     */
    void send();

    /**
     * 4.修改状态
     */
    void updateStatus();
}
