package com.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map 基本操作演示
 */
public class MapDemo {

    public static void main(String[] args) {
        // 1. 创建 Map（LinkedHashMap 保持插入顺序）
        Map<String, Object> movieInfo = new LinkedHashMap<>();

        // 2. 添加键值对
        movieInfo.put("movieName", "流浪地球");
        movieInfo.put("director", "郭帆");
        movieInfo.put("score", 7.9);
        movieInfo.put("year", 2019);

        // 3. 获取值
        String name = (String) movieInfo.get("movieName");  // "流浪地球"

        // 4. 判断 key 是否存在
        boolean hasScore = movieInfo.containsKey("score");  // true

        // 5. 获取所有 key
        Set<String> keys = movieInfo.keySet();  // [movieName, director, score, year]

        // 6. 遍历 Map
        for (Map.Entry<String, Object> entry : movieInfo.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
