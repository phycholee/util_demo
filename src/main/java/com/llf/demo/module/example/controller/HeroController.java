package com.llf.demo.module.example.controller;

import com.llf.demo.common.JsonData;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Oliver.li
 * @Description:
 * @date: 2018/5/8 14:07
 */
@RestController
@RequestMapping("/api")
public class HeroController {

    private List<Map<String, Object>> heroes;

    {
        heroes = new LinkedList<>();
        Map<String, Object> hero1 = new HashMap<>();
        hero1.put("id", 11);
        hero1.put("name", "Mr. Nice");
        Map<String, Object> hero2 = new HashMap<>();
        hero2.put("id", 12);
        hero2.put("name", "Narco");
        Map<String, Object> hero3 = new HashMap<>();
        hero3.put("id", 13);
        hero3.put("name", "Bombasto");
        Map<String, Object> hero4 = new HashMap<>();
        hero4.put("id", 14);
        hero4.put("name", "Celeritas");
        Map<String, Object> hero5 = new HashMap<>();
        hero5.put("id", 15);
        hero5.put("name", "Magneta");
        Map<String, Object> hero6 = new HashMap<>();
        hero6.put("id", 16);
        hero6.put("name", "RubberMan");
        Map<String, Object> hero7 = new HashMap<>();
        hero7.put("id", 17);
        hero7.put("name", "Dynama");
        Map<String, Object> hero8 = new HashMap<>();
        hero8.put("id", 18);
        hero8.put("name", "Dr IQ");
        Map<String, Object> hero9 = new HashMap<>();
        hero9.put("id", 19);
        hero9.put("name", "Magma");
        Map<String, Object> hero10 = new HashMap<>();
        hero10.put("id", 20);
        hero10.put("name", "Tornado");

        heroes.add(hero1);
        heroes.add(hero2);
        heroes.add(hero3);
        heroes.add(hero4);
        heroes.add(hero5);
        heroes.add(hero6);
        heroes.add(hero7);
        heroes.add(hero8);
        heroes.add(hero9);
        heroes.add(hero10);
    }

    @GetMapping("/hero")
    public List<Map<String, Object>> list(String name){

        if (!StringUtils.isEmpty(name)){
            return heroes.stream().filter(map -> MapUtils.getString(map, "name").contains(name)).collect(Collectors.toList());
        }

        return heroes;
    }

    @GetMapping("/hero/{id}")
    public Map<String, Object> get(@PathVariable Integer id){
        Optional<Map<String, Object>> hero = heroes.stream().filter(map -> id.equals(map.get("id"))).findFirst();
        return hero.orElse(null);
    }

    @PutMapping("/hero")
    public JsonData update(@RequestBody Map<String, Object> params){
        Integer id = MapUtils.getInteger(params, "id");
        String name = MapUtils.getString(params, "name");

        heroes = heroes.stream().peek(map -> {
            if (id.equals(map.get("id"))) {
                map.replace("id", id);
                map.replace("name", name);
            }
        }).collect(Collectors.toList());

        return JsonData.success("update success", null);
    }

    @PostMapping("/hero")
    public JsonData save(@RequestBody Map<String, Object> params){
        Map<String, Object> map = heroes.get(heroes.size() - 1);
        Integer lastId = MapUtils.getInteger(map, "id");

        params.put("id", ++lastId);
        heroes.add(params);

        return JsonData.success("save success", null);
    }

    @DeleteMapping("/hero/{id}")
    public JsonData delete(@PathVariable Integer id){

        heroes.removeIf(map -> id.equals(map.get("id")));

        return JsonData.success("delete success", null);
    }

    @GetMapping("/string")
    public String string(){

        StringBuilder str = new StringBuilder();

        for (int i = 0; i <= 50000; i++){
            str.append("ouHYWwZcBS4wVWv8nMnv7aZaXu1H").append(",");
        }

        if (str.length() > 0) {
            throw new RuntimeException("你过不去的");
        }

        return str.toString();
    }
}
