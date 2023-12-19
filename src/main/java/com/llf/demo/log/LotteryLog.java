package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.llf.demo.util.ExcelUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Oliver.li
 * @date 2023/9/26 18:24
 */
public class LotteryLog {

    public static void main(String[] args) throws Exception {
        lotteryLog();
    }

    public static void lotteryLog() throws Exception {
        Pattern compile = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}).*req:(.*)resp:(.*)");
        List<String> lines = FileUtils.readLines(new File("C:\\Users\\lilingfeng\\Downloads\\battleLog\\drawSuccess.json"));
        List<Map<String, String>> list = new ArrayList<>();
        lines.stream().skip(1)
                .map(line -> {
                    JSONObject jsonObject = JSON.parseObject(line);
                    return jsonObject.getString("message");
                }).forEach(msg -> {
                    Matcher matcher = compile.matcher(msg);
                    matcher.find();
                    System.out.println(matcher.group(1));
                    //System.out.println(matcher.group(2));
                    String trim = matcher.group(2).trim();
                    String uid = JSON.parseObject(trim.substring(0, trim.length() - 1)).getString("uid");
                    System.out.println(uid);
                    //System.out.println(matcher.group(3));
                    JSONArray resultPrizes = JSON.parseObject(matcher.group(3)).getJSONArray("resultPrize");
                    for (int i = 0; i < resultPrizes.size(); i++) {
                        JSONObject jsonObject = resultPrizes.getJSONObject(i);
                        String prizeName = jsonObject.getJSONObject("prize").getString("name");
                        Integer prizeNum = jsonObject.getInteger("prizeNum");

                        Map<String, String> map = new HashMap<>();
                        map.put("time", matcher.group(1));
                        map.put("uid", uid);
                        map.put("prizeName", prizeName);
                        map.put("prizeNum", prizeNum + "");
                        list.add(map);
                    }
                });
        File file = new File("C:\\Users\\lilingfeng\\Downloads\\battleLog\\lottery-result.xls");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String[] titles = {"时间", "uid", "奖励名称", "奖励数量"};
        String[] fields = {"time", "uid", "prizeName", "prizeNum"};
        ExcelUtil.exportFile(list, titles, fields, fileOutputStream);
    }
}
