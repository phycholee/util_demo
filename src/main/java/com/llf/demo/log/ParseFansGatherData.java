package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llf.demo.log.domain.FansGather;
import com.llf.demo.log.domain.FansGatherExport;
import com.llf.demo.log.domain.FansGatherUser;
import com.llf.demo.util.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oliver.li
 * @date 2023/5/26 15:12
 */
public class ParseFansGatherData {

    public static void main(String[] args) throws IOException {
        String text = FileUtils.readFileToString(new File("C:\\Users\\lilingfeng\\Downloads\\FansGather.txt"), "UTF-8");
        String userJson = FileUtils.readFileToString(new File("C:\\Users\\lilingfeng\\Downloads\\FansGatherUser.json"), "UTF-8");
        parse(text, userJson);
    }

    public static void parse(String data, String userJson){
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
            Map<String, List<FansGatherUser>> userMap = parseUser(userJson);

            String jsonOutput = objectMapper.writeValueAsString(jsonArray);
            System.out.println(jsonOutput);

            List<FansGather> list = JSON.parseArray(jsonOutput, FansGather.class);
            Map<String, List<FansGather>> dayMap = list.stream().collect(Collectors.groupingBy(f -> DateFormatUtils.format(f.getCreateTime(), "yyyyMMdd")));

            List<FansGatherExport> excelList = new ArrayList<>();
            for (Map.Entry<String, List<FansGather>> entry : dayMap.entrySet()) {
                Set<Long> anchorSet = new HashSet<>();
                Set<Long> anchorRubySet = new HashSet<>();
                int rubyAmount = 0;
                for (FansGather gather : entry.getValue()) {
                    anchorSet.add(gather.getAuid());
                    if (gather.getRubyAmount() > 0){
                        anchorRubySet.add(gather.getAuid());
                    }
                    rubyAmount += gather.getRubyAmount();
                }
                List<FansGatherUser> userList = userMap.get(entry.getKey());
                long userCount = 0;
                if (CollectionUtils.isNotEmpty(userList)){
                    userCount = userList.stream().map(FansGatherUser::getUid).distinct().count();
                }

                FansGatherExport export = new FansGatherExport(entry.getKey(), anchorSet.size(), anchorRubySet.size(), 0, rubyAmount, (int) userCount, 0, 0);
                excelList.add(export);
            }

            File file = new File("C:\\Users\\lilingfeng\\Downloads\\真爱召集数据0512.xls");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ExcelUtil.exportFile(excelList, fileOutputStream, FansGatherExport.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<FansGatherUser>> parseUser(String userJson){
        List<FansGatherUser> list = new ArrayList<>();
        JSONArray array = JSON.parseArray(userJson);
        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            String line = object.getString("line");
            list.add(parseFansGatherUser(line));
        }
        return list.stream().collect(Collectors.groupingBy(FansGatherUser::getTime));
    }

    private static FansGatherUser parseFansGatherUser(String input){
        // 解析时间
        String times = input.substring(0, 10);

        // 解析auid
        String auidIdentifier = "auid=";
        int auidStartIndex = input.indexOf(auidIdentifier) + auidIdentifier.length();
        int auidEndIndex = input.indexOf(",", auidStartIndex);
        String auid = input.substring(auidStartIndex, auidEndIndex);

        // 解析uid
        String uidIdentifier = ",uid=";
        int uidStartIndex = input.indexOf(uidIdentifier) + uidIdentifier.length();
        int uidEndIndex = input.indexOf(",", uidStartIndex);
        String uid = input.substring(uidStartIndex, uidEndIndex);

        // 解析gatherId
//        String gatherIdIdentifier = "gatherId=";
//        int gatherIdStartIndex = input.indexOf(gatherIdIdentifier) + gatherIdIdentifier.length();
//        String gatherId = input.substring(gatherIdStartIndex, input.length() - 1);

        return new FansGatherUser(Long.parseLong(auid), Long.parseLong(uid), 0L, times.replaceAll("-", ""));
    }
}
