package com.llf.demo.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Oliver.li
 * @date 2023/2/9 17:10
 */
public class ScanLog {

    public static void main(String[] args) throws Exception {
        String rankId = "2023SpringBattle_32_UserRank_141";
        System.out.println((rankId.hashCode() / 100) % 4);
        System.out.println(rankId.hashCode() % 100);

        System.out.println();

        long targetId = 2927139335L;
        System.out.println((targetId / 100) % 4);
        System.out.println(targetId % 100);
    }

    private static void trace(){
        Set<String> traceIdSet = new HashSet<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lilingfeng\\Downloads\\rewardUid.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\lilingfeng\\Downloads\\rewardUid1.txt"))
        ){

            String line = reader.readLine();
            while (line != null){
                boolean add = traceIdSet.add(line);
                if (add){
                    writer.write(line + "\n");
                }
                line = reader.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void uid(){
        Set<String> uidSet = new HashSet<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lilingfeng\\Downloads\\uidline.txt"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\lilingfeng\\Downloads\\uid2.txt"))
        ){

            String line = reader.readLine();
            while (line != null){
                boolean add = uidSet.add(line);
                if (add){
                    writer.write(line + "\n");
                }
                line = reader.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void readTrace(){
        Set<String> traceIdSet = new HashSet<>();
        Pattern compile = Pattern.compile("167\\d+-\\d+");
        try (
                BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lilingfeng\\Downloads\\trace2.txt"));
                BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\lilingfeng\\Downloads\\uid.json"));
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\lilingfeng\\Downloads\\uid.txt"))
        ){

            String line = reader.readLine();
            while (line != null){
                traceIdSet.add(line);
                line = reader.readLine();
            }

            String line1 = reader2.readLine();
            while (line1 != null){
                Matcher matcher = compile.matcher(line1);
                if (matcher.find()){
                    String group = matcher.group();
                    if (traceIdSet.contains(group)){
                        writer.write(line1 + "\n");
                    }
                }
                line1 = reader2.readLine();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
