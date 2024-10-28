package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llf.demo.util.ExcelUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oliver.li
 * @date 2023/5/26 15:12
 */
public class ParseMysqlData {

    public static void main(String[] args) throws IOException {
        String text = FileUtils.readFileToString(new File("C:\\Users\\lilingfeng\\Downloads\\秋季赛银河列车瓜分奖金.txt"), "UTF-8");
        parse(text);
    }

    public static void parse(String data){
        String[] lines = data.split("\n");
        String[] headers = lines[0].trim().split("\\|");

        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> jsonArray = new ArrayList<>();

        for (int i = 2; i < lines.length; i++) {
            String[] values = lines[i].trim().split("\\|");
            Map<String, Object> jsonMap = new LinkedHashMap<>();
            for (int j = 0; j < headers.length; j++) {
                String key = headers[j].trim();
                String value = values[j].trim();
                jsonMap.put(key, value);
            }
            jsonArray.add(jsonMap);
        }

        try {
            String jsonOutput = objectMapper.writeValueAsString(jsonArray);
            System.out.println(jsonOutput);

            File file = new File("C:\\Users\\lilingfeng\\Downloads\\2024秋季赛银河列车瓜分奖金.xls");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String[] titles = {"日期", "uid", "站台", "排名", "分奖红宝石总价值", "发奖时间"};
            String[] fields = {"createDate", "uid", "target_station", "rank_no", "award_total_amount", "create_time"};
            ExcelUtil.exportFile(JSON.parseArray(jsonOutput), titles, fields, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
