package com.llf.demo.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
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
        String text = FileUtils.readFileToString(new File("D:\\download\\人气嘉年华兑换记录-整个活动周期内.txt"), "UTF-8");
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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
