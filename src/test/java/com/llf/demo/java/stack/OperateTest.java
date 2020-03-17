package com.llf.demo.java.stack;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2019/7/30 13:58
 */
public class OperateTest {

    private static final Pattern NUMBER_DOT_PATTERN = Pattern.compile("[0-9.]");


    public static void main(String[] args) throws EvaluationException {

        Map<String, String> userOptionMap = new HashMap<>();
        userOptionMap.put("1.3", "Y");
        userOptionMap.put("2.1", "Y");
        userOptionMap.put("3.4", "Y");
        userOptionMap.put("10.10", "Y");

        String rule = "( [1.3] * ([2.1]+\n" +
                " [2.2]) * [3.4])  *    --    （[10.10]）";

        String parse = parse(rule, userOptionMap);

        System.out.println(parse);

        Evaluator evaluator = new Evaluator();
        double result = Double.parseDouble(evaluator.evaluate(parse));
        System.out.println((int)result);
    }

    /**
     * 将[]里的值匹配用户答案，转换为0或1
     * @param rule
     * @return
     */
    private static String parse(String rule, Map<String, String> userOptionMap){

        if (rule == null || userOptionMap == null){
            return null;
        }

        StringBuilder result = new StringBuilder();

        char[] chars = rule.toCharArray();

        LinkedList<String> bracketStack = new LinkedList<>();
        LinkedList<String> optionStack = new LinkedList<>();
        for (char c : chars) {
            String str = c + "";
            if("(".equals(str) || ")".equals(str) || "+".equals(str) || "*".equals(str)){
                result.append(str);
            } else if ("（".equals(str)){
                result.append("(");
            } else if ("）".equals(str)){
                result.append(")");
            } else if ("[".equals(str)){
                bracketStack.push(str);
            } else if ("]".equals(str)){
                //匹配了中括号，就匹配用户答案
                if ("[".equals(bracketStack.peek())){
                    int score = matchUserOption(optionStack, userOptionMap);
                    result.append(score);
                    bracketStack.pop();
                }
            } else if (NUMBER_DOT_PATTERN.matcher(str).matches()){
                optionStack.push(str);
            }
        }

        return result.toString();
    }

    /**
     * 匹配用户的
     * @param optionStack
     * @param userOptionMap
     * @return
     */
    private static int matchUserOption(LinkedList<String> optionStack, Map<String, String> userOptionMap){
        if (optionStack == null){
            return 0;
        }

        StringBuilder popStr = new StringBuilder();

        while (optionStack.peek() != null){
            popStr.append(optionStack.pop());
        }

        //单个选项，即[1.1]里的内容
        String rule = popStr.reverse().toString().trim();
        System.out.println("matchUserOption=" + rule);

        String userOption = userOptionMap.get(rule);

        return "Y".equals(userOption) ? 1 : 0;
    }
}
