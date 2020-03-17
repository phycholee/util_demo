package com.llf.demo.json;

import com.alibaba.fastjson.JSON;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/1/8 11:25
 */
public class BravoDto {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonString(){
        return JSON.toJSONString(this);
    }
}
