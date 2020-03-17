package com.llf.demo.java.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/1/8 11:24
 */
public class FastJsonTest {


    @Test
    public void testJson(){

        BravoDto dto = new BravoDto();
        dto.setId(1L);
        dto.setName("aa");

        System.out.println(JSON.toJSONString(dto));

    }

}
