package com.llf.demo.pattern.push;

import org.json.JSONObject;

import java.util.List;

/**
 * @author: Oliver.li
 * @Description: 准备数据步骤
 * @date: 2020/4/10 16:29
 */
public interface PrepareStrategy {

    /**
     * 1.从solr搜索数据
     */
    List<String> listDataFromSolr();

    /**
     * 2.过滤数据，可合并到3中
     */
    void filterData();

    /**
     * 3.拼装数据
     */
    List<JSONObject> buildData(List<String> list);

    /**
     * 4.保存数据到队列
     */
    void push(List<JSONObject> list);

    /**
     * 5.修改状态
     */
    void updateStatus();
}
