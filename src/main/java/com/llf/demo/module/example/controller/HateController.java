package com.llf.demo.module.example.controller;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/4/13 18:22
 */
@RestController
@RequestMapping("/hate")
public class HateController {

    private static Logger logger = LoggerFactory.getLogger(HateController.class);

    private static final String[] STRS = {"aa", "bb", "dd", "gg", "jj", "vv", "yy"};

    @RequestMapping(value = "/point", method = RequestMethod.GET)
    public Map<String, Object> point(String name){
        logger.info(name);
        Map<String, Object> map = new HashMap<>(4);
        map.put("echo", "I will always hate you " + name);
        return map;
    }

    @PostMapping("/out")
    public Map<String, Object> out(@RequestBody Map<String, Object> params){

        String name = MapUtils.getString(params, "name");

        logger.info(name);

        Map<String, Object> map = new HashMap<>(4);
        map.put("reply", "I've receive all you message, if you hate somebody, then do it, hate him/her like you love him/her.");

        return map;
    }

}
