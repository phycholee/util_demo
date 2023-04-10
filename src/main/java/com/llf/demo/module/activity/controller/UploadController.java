package com.llf.demo.module.activity.controller;

import com.llf.demo.common.JsonData;
import com.llf.demo.util.ExcelUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2020/2/25 10:12
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @PostMapping("/excel")
    public JsonData get(@RequestParam("file") MultipartFile file) throws Exception {
        String[] fields = {"encry_aid", "order_id", "pay_time", "goods_type", "goods_num"};
        List<Map<String, Object>> list = ExcelUtil.read(file.getInputStream(), fields);

        List<Map<String, Object>> list2 = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (Map<String, Object> map : list) {
            String orderId = MapUtils.getString(map, "order_id");
            if (!set.add(orderId)){
                continue;
            }
            map.remove("pay_time");
            list2.add(map);
        }
        return JsonData.success(list2);
    }


}
