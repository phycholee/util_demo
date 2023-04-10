package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Oliver.li
 * @date 2023/3/23 15:42
 */
public class AddScore {

    public static void main(String[] args) {

        String json = "[{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":3000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":3000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":3000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":8000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":12000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":30000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":40000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":15000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":25000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":5000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":15000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":25000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":40000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":60000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":100000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":150000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":200000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":300000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":400000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":2000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10900}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":1000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":1152027979, \"usedUid\":2731500683, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":30000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":30000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2821595002, \"anchorScore\":40000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":100000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":338500}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":410000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":90000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":468000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":78000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":20000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":150000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899239680, \"usedUid\":2821595002, \"anchorScore\":500000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2899199868, \"usedUid\":2731500683, \"anchorScore\":10000}\n" +
                ",{\"anchorUid\":2291016871, \"usedUid\":2678758733, \"anchorScore\":100}]";

        Map<Long, Long> anchorScoreMap = new HashMap<>();
        Map<Long, Long> userScoreMap = new HashMap<>();
        JSONArray array = JSON.parseArray(json);
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            Long anchorUid = jsonObject.getLong("anchorUid");
            Long usedUid = jsonObject.getLong("usedUid");
            Long score = jsonObject.getLong("anchorScore");

            anchorScoreMap.merge(anchorUid, score, Long::sum);
            userScoreMap.merge(usedUid, score, Long::sum);
        }
        System.out.println(JSON.toJSONString(anchorScoreMap));
        System.out.println(JSON.toJSONString(userScoreMap));
    }

}
