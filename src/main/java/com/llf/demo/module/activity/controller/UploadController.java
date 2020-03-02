package com.llf.demo.module.activity.controller;

import com.llf.demo.common.JsonData;
import com.llf.demo.util.ExcelUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

        String[] fields = {"id","name", "url"};
        List<Map<String, Object>> list = ExcelUtil.read(file.getInputStream(), fields);

        for (Map<String, Object> map : list) {
            map.put("singer", "纯音乐");
        }

        return JsonData.success(list);
    }


}
