package com.llf.demo.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.llf.demo.log.domain.GenericBaseEvent;
import com.llf.demo.log.domain.RubyRechargeOrder;
import com.llf.demo.log.domain.RubyRechargeWaterFlow;
import com.llf.demo.util.ExcelUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Oliver.li
 * @date 2023/9/15 11:37
 */
public class RubyChargeLogAdd {

    private static final Map<Long, Long> COUNT_MAP = new HashMap<>();
    private static final Map<Long, Long> TODAY_VALUE = new HashMap<>();
    private static final Map<Long, Long> CURRENT_AMOUNT = new HashMap<>();
    private static final Set<Long> LIMIT_SET = new HashSet<>();

    private static final String COUNT_JSON = "{\"2930115892\":3,\"2967733129\":1,\"1464863398\":1,\"2731754550\":3,\"2967824023\":1,\"2961899279\":2,\"2829892185\":2,\"2956281447\":3,\"2882273782\":1,\"2965754676\":10,\"2710879013\":7,\"2869659340\":1,\"2951894429\":3,\"2956837630\":1,\"2965334117\":10,\"2931898903\":3,\"2796499055\":1,\"2778192420\":1,\"2942824104\":2,\"63277603\":1,\"2965773505\":10,\"2938497799\":1,\"2939670031\":2,\"2946738163\":3,\"2961701648\":1,\"2879919817\":1,\"2965459610\":10,\"2900830953\":1,\"2956395599\":10,\"2913790892\":20,\"2955092372\":2,\"2725493041\":3,\"2781012201\":1,\"2957440772\":1,\"2798910608\":1,\"2954989058\":1,\"2713291505\":7,\"2954910055\":3,\"2765284432\":1,\"331521083\":5,\"2887480413\":2,\"2944777927\":10,\"2945280507\":4,\"2713841731\":3,\"2967604968\":3,\"2616535967\":3,\"2734810642\":1,\"2957576809\":2,\"2942846384\":1,\"2944749091\":10,\"2713619648\":1,\"2961924552\":3,\"2963469571\":10,\"2811199146\":3,\"2965759237\":10,\"2723259195\":2,\"2710878877\":10,\"2952505980\":1,\"2930265445\":1,\"2956983192\":1,\"2781928111\":1,\"73608816\":1,\"2965459480\":10,\"2213462620\":4,\"2744670716\":3,\"2920501742\":3,\"2965008366\":10,\"2947617342\":3,\"2967125632\":2,\"2880365564\":1,\"2690163606\":2,\"2962933031\":2,\"2920072537\":14,\"2753085356\":4,\"2930122311\":3,\"2716759420\":10,\"2960719616\":4,\"2713791043\":1,\"2914211125\":4,\"2961598180\":1,\"2953575202\":2,\"2966171530\":3,\"2784780415\":10,\"2851450847\":1,\"2713476997\":1,\"2908522440\":1,\"2889474038\":1,\"2965459386\":20,\"2807183389\":2,\"2923198189\":3,\"2731999247\":3,\"2889945518\":4,\"2967827382\":1,\"2930016010\":3,\"2956319568\":1,\"2957420078\":1,\"2965148784\":10,\"2890878136\":10,\"2933470776\":3,\"2903283313\":2,\"2696717224\":1,\"2713381332\":1,\"2967677649\":3,\"2850562115\":2,\"2707946933\":1,\"2962830520\":4,\"2965459433\":20,\"2965459679\":10,\"2713158572\":1,\"2743722342\":1,\"1112783548\":7,\"2956712212\":9,\"650503298\":1,\"2921880804\":3,\"1329666401\":10,\"2960955929\":3,\"2951119159\":1,\"2776532368\":1,\"2918910102\":1,\"2719744878\":9,\"2956594531\":1,\"66503412\":1,\"2964299336\":10,\"2943613396\":7,\"2943020303\":2,\"2728502629\":1,\"2935609575\":2,\"2965466416\":1,\"2710739116\":1,\"2913953193\":2,\"2946470667\":12,\"2726050211\":1,\"2781363469\":1,\"2713595097\":1,\"2912300192\":10,\"2966189666\":10,\"2943848040\":2,\"2965459333\":10,\"2957001666\":1,\"2966669560\":2,\"2961702013\":1,\"2962715543\":1,\"2946324914\":1,\"2932503134\":2,\"2898413587\":1,\"2930069849\":1,\"2882483740\":5,\"2906464457\":10,\"2713717051\":3,\"2847354955\":2,\"2954678285\":3,\"2915150007\":1,\"2902669184\":2,\"2967127758\":2,\"2716931715\":2,\"2713687079\":6,\"2942332198\":2,\"2965755714\":3,\"2941002063\":1,\"2954543542\":1,\"2728143345\":1,\"2955019938\":9,\"2713530331\":2,\"2961545704\":1,\"2735684762\":10,\"2710268347\":5,\"2953460952\":1,\"2713501270\":3,\"81155811\":1,\"2921941927\":2,\"2744783109\":4,\"2760978918\":2,\"2930069220\":3,\"2952390838\":9,\"2965138368\":10,\"2939609480\":1,\"2731930542\":5,\"1559245475\":5,\"2732133411\":1,\"2953683569\":2,\"2962710014\":1,\"2965881973\":10,\"2952491527\":3,\"2713262914\":10,\"2731089770\":1,\"2890336326\":4,\"2942870040\":1,\"2810159354\":2,\"2695169173\":2,\"2938522597\":4,\"2748461610\":2,\"2931146218\":2,\"2713756393\":3,\"2716236727\":3,\"2439280978\":1,\"2944403874\":1,\"2832138483\":2,\"2713414803\":5,\"2848217669\":3,\"981889994\":2,\"2752068670\":1,\"2715912083\":3,\"2966117772\":1,\"2713289358\":1,\"2923108106\":1,\"2924594469\":10,\"2953891357\":1,\"2921041872\":1,\"2961598586\":1,\"2967863282\":1,\"2965764064\":10,\"2909542890\":1,\"2933520337\":9,\"2695173408\":1}";

    public static void main(String[] args) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lilingfeng\\Downloads\\battleLog\\rubyCharge14.json"));
        ){

            String line = reader.readLine();
            while (line != null){
                handleLine(line);
                line = reader.readLine();
            }

            JSONObject countJson = JSON.parseObject(COUNT_JSON);

            List<Map<String, Long>> list = new ArrayList<>();
            for (Map.Entry<Long, Long> entry : COUNT_MAP.entrySet()) {
                Map<String, Long> map = new HashMap<>();
                map.put("uid", entry.getKey());
                map.put("oldCount", countJson.getLongValue(entry.getKey().toString()) * 10);
                map.put("newCount", entry.getValue() * 10);
                map.put("gap", map.get("newCount") - map.get("oldCount"));
                list.add(map);
            }
            list = list.stream().filter(m -> {
                Long gap = m.get("gap");
                return gap != 0L;
            }).collect(Collectors.toList());

            File file = new File("C:\\Users\\lilingfeng\\Downloads\\battleLog\\rightCount14.xls");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String[] titles = {"uid", "实际发送福利值", "应该发送福利值", "差值"};
            String[] fields = {"uid", "oldCount", "newCount", "gap"};
            ExcelUtil.exportFile(list, titles, fields, fileOutputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void handleLine(String line){
        String msg = RubyChargeLogParse.parseLog(line);
        if (StringUtils.isEmpty(msg)){
            System.out.println("msg is empty");
            return;
        }
        msg = StringEscapeUtils.unescapeJava(msg);
        GenericBaseEvent<RubyRechargeWaterFlow> event = new Gson().fromJson(msg, new TypeToken<GenericBaseEvent<RubyRechargeWaterFlow>>() {}.getType());
        RubyRechargeWaterFlow waterFlow = event.getData();
        RubyRechargeOrder order = waterFlow.getOrder();
        if (LIMIT_SET.contains(order.getId())){
            return;
        }
        LIMIT_SET.add(order.getId());

        long uid = order.getUid();
        int amount = new BigDecimal(order.getAmount()+"").multiply(new BigDecimal("1000")).intValue();

        long chargeFuliValue = TODAY_VALUE.getOrDefault(uid, 0L);
        long chargeMaxValue = 100;
        if (chargeFuliValue >= chargeMaxValue){
            System.out.println("handleCharge, reach chargeMaxValue, uid="+uid);
            return;
        }

        int chargeAmount = 10000;
        Long preAmount = CURRENT_AMOUNT.getOrDefault(uid, 0L);
        long currentAmount = preAmount + amount;
        CURRENT_AMOUNT.put(uid, currentAmount);
        long currentNum = currentAmount / chargeAmount;
        long preNum = preAmount / chargeAmount;
        if (currentNum == preNum || currentNum <= 0){
            return;
        }
        Long count = COUNT_MAP.getOrDefault(uid, 0L);
        COUNT_MAP.put(uid, count + 1);

        Long value = TODAY_VALUE.getOrDefault(uid, 0L);
        TODAY_VALUE.put(uid, value + 10);
    }
}
