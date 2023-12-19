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
public class IncrFuliLog {

    public static void main(String[] args) throws Exception {
        lotteryLog();
    }

    public static void lotteryLog() throws Exception {
        Pattern compile = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}).*incrFuliValue key=actBattle:user_fuli_value:148:(.*)");
        List<String> lines = FileUtils.readLines(new File("C:\\Users\\lilingfeng\\Downloads\\battleLog\\incrFuliValue.json"));
        List<Map<String, String>> list = new ArrayList<>();
        lines.stream().skip(1)
                .map(line -> {
                    JSONObject jsonObject = JSON.parseObject(line);
                    return jsonObject.getString("message");
                }).forEach(msg -> {
                    Matcher matcher = compile.matcher(msg);
                    matcher.find();
                    String time = matcher.group(1);
                    String trim = matcher.group(2).trim();

                    String[] split = trim.split(",");
                    String uid = split[0];
                    String value = split[1].replace("addValue=", "");
                    Map<String, String> map = new HashMap<>();
                    map.put("time", time);
                    map.put("uid", uid);
                    map.put("value", value);
                    list.add(map);
                    System.out.println(JSON.toJSONString(map));
                });
        File file = new File("C:\\Users\\lilingfeng\\Downloads\\battleLog\\incrFuliValue.xls");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String[] titles = {"时间", "uid", "福利值"};
        String[] fields = {"time", "uid", "value"};
        ExcelUtil.exportFile(list, titles, fields, fileOutputStream);
    }
}
