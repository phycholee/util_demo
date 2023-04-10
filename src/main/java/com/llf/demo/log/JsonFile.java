package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oliver.li
 * @date 2023/2/21 15:48
 */
public class JsonFile {

    public static void main(String[] args) throws Exception {
        wishAct();
    }

    public static void wishAct(){
        List<Long> anchorIds = new ArrayList<>();
        anchorIds.add(2439260685L);
        anchorIds.add(2889570393L);
        anchorIds.add(2852594754L);
        anchorIds.add(2733954319L);
        anchorIds.add(2861021941L);
        anchorIds.add(2877844510L);
        anchorIds.add(2869225402L);
        anchorIds.add(2854034625L);
        anchorIds.add(2869329883L);
        anchorIds.add(2860156169L);
        anchorIds.add(2899588279L);
        anchorIds.add(1783934087L);
        anchorIds.add(2849021541L);
        anchorIds.add(2862325347L);
        anchorIds.add(2870042182L);
        anchorIds.add(2921443090L);
        anchorIds.add(2869349114L);
        anchorIds.add(2901898620L);
        anchorIds.add(2793441334L);
        anchorIds.add(2859496723L);
        anchorIds.add(2863793350L);
        anchorIds.add(2885390345L);
        anchorIds.add(2919558466L);
        anchorIds.add(2909167980L);

        Map<Long, List<Long>> scoreMap = new HashMap<>();

        try {
            String json1 = FileUtils.readFileToString(new File("C:\\Users\\lilingfeng\\Downloads\\wishFirst.json"), "UTF-8");
            String json2 = FileUtils.readFileToString(new File("C:\\Users\\lilingfeng\\Downloads\\wishHalf.json"), "UTF-8");

            JSONArray array1 = JSON.parseArray(json1);
            for (int i = 0; i < array1.size(); i++) {
                JSONObject object = array1.getJSONObject(i);
                Long uid = object.getLong("uid");
                if (anchorIds.contains(uid)){
                    scoreMap.computeIfAbsent(uid, k -> new ArrayList<>()).add(object.getLong("score"));
                }
            }

            JSONArray array2 = JSON.parseArray(json2);
            for (int i = 0; i < array2.size(); i++) {
                JSONObject object = array2.getJSONObject(i);
                Long uid = object.getLong("uid");
                if (anchorIds.contains(uid)){
                    scoreMap.computeIfAbsent(uid, k -> new ArrayList<>()).add(object.getLong("score"));
                }
            }

            for (Long anchorId : anchorIds) {
                List<Long> list = scoreMap.get(anchorId);
                System.out.println("uid=" + anchorId + ", scores=" + list);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
