package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Oliver.li
 * @date 2023/9/15 11:37
 */
public class RubyChargeLogSuccessParse {

    private static final Map<String, Integer> UID_COUNT_MAP = new HashMap<>();
    private static final Set<String> LIMIT_SET = new HashSet<>();

    private static final Pattern pattern = Pattern.compile("\\ 18a[0-9a-fA-F]+-[0-9]+\\b");

    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lilingfeng\\Downloads\\battleLog\\addfuli14.json"));
        ){

            String line = reader.readLine();
            while (line != null){
                handleLine(line);
                line = reader.readLine();
            }
            FileUtils.writeStringToFile(new File("C:\\Users\\lilingfeng\\Downloads\\battleLog\\uidCount14.json"), JSON.toJSONString(UID_COUNT_MAP), StandardCharsets.UTF_8);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void handleLine(String line){
        String uid = parseLog(line);
        if (StringUtils.isEmpty(uid)){
            System.out.println("uid is empty");
            return;
        }

        String traceId = parseTraceId(line);
        if (!StringUtils.isEmpty(traceId) && LIMIT_SET.contains(traceId)){
            return;
        }
        LIMIT_SET.add(traceId);

        Integer count = UID_COUNT_MAP.getOrDefault(uid, 0);
        UID_COUNT_MAP.put(uid, count + 1);
    }

    private static String parseLog(String log){
        int messageStart = log.indexOf("\"message\":\"") + "\"message\":\"".length();

        int messageEnd = log.indexOf("\"}", messageStart);

        String message = log.substring(messageStart, messageEnd);

        int uidStart = message.indexOf("uid=") + "uid=".length();

        int uidEnd = message.indexOf(",", uidStart);
        if (uidEnd == -1) {
            uidEnd = message.length();
        }
        return message.substring(uidStart, uidEnd);
    }

    private static String parseTraceId(String log){
        // 使用 Gson 解析 JSON
        JsonObject jsonObject = JsonParser.parseString(log).getAsJsonObject();
        String message = jsonObject.get("message").getAsString();

        // 使用正则表达式匹配
        Matcher matcher = pattern.matcher(message);

        // 查找匹配项
        if (matcher.find()) {
            String matchedString = matcher.group();
            System.out.println("Matched String: " + matchedString);
            return matchedString;
        } else {
            System.out.println("No match found.");
            return null;
        }
    }
}
