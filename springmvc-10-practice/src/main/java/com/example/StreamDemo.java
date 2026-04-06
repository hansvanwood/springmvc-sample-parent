package com.example;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo {
    /**
     * Stream API 综合演示
     * 模拟对电影数据的加工处理
     */
    public static void main(String[] args) {
        // 模拟从数据库查询出的电影评分列表
        List<Double> scores = List.of(9.7, 8.5, 7.2, 6.8, 9.1, 8.8, 5.5, 7.9, 8.2, 9.3);

        // 1. filter：筛选出评分 >= 8.0 的高分电影
        List<Double> highScores = scores.stream()
                .filter(score -> score >= 8.0)
                .collect(Collectors.toList());
        // 结果: [9.7, 8.5, 9.1, 8.8, 8.2, 9.3]

        // 2. sorted：按评分降序排列
        List<Double> sortedScores = highScores.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        // 结果: [9.7, 9.3, 9.1, 8.8, 8.5, 8.2]

        // 3. limit：只取 Top 3
        List<Double> top3 = sortedScores.stream()
                .limit(3)
                .collect(Collectors.toList());
        // 结果: [9.7, 9.3, 9.1]

        // 4. map + collect：将评分转为描述文字
        List<String> descriptions = top3.stream()
                .map(score -> "评分: " + score + " ⭐")
                .collect(Collectors.toList());
        // 结果: ["评分: 9.7 ⭐", "评分: 9.3 ⭐", "评分: 9.1 ⭐"]

        // 5. 链式写法（推荐）：以上步骤可以合并为一条链式调用
        List<String> result = scores.stream()
                .filter(score -> score >= 8.0)       // 过滤
                .sorted(Comparator.reverseOrder())    // 排序
                .limit(3)                             // 截取
                .map(score -> "评分: " + score + " ⭐") // 转换
                .collect(Collectors.toList());        // 收集

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("原始评分列表", scores);
        data.put("Top3 高分描述", result);
        System.out.println(data);
    }
}
