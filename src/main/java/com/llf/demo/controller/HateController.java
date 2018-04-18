package com.llf.demo.controller;

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

    private static final String[] STRS = {"aa", "bb", "dd", "gg", "jj", "vv", "yy"};

    @RequestMapping (value = "/point", method = RequestMethod.GET)
    public Map<String, Object> point(String name){
        System.out.println(name);
        Map<String, Object> map = new HashMap<>(4);
        map.put("echo", "I will always hate you " + name);
        return map;
    }

}
