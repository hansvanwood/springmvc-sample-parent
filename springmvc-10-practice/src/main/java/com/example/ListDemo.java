package com.example;

import java.util.ArrayList;
import java.util.List;
/**
 * List 基本操作演示
 */
public class ListDemo {

    public static void main(String[] args) {
        // 1. 创建 List
        List<String> movieTypes = new ArrayList<>();

        // 2. 添加元素
        movieTypes.add("剧情");
        movieTypes.add("喜剧");
        movieTypes.add("动作");
        movieTypes.add("科幻");
        movieTypes.add("动画");

        // 3. 获取元素（索引从 0 开始）
        String first = movieTypes.get(0);  // "剧情"

        // 4. 获取列表大小
        int size = movieTypes.size();  // 5

        // 5. 判断是否包含某元素
        boolean hasSF = movieTypes.contains("科幻");  // true

        // 6. 删除元素
        movieTypes.remove("喜剧");  // 按值删除
        movieTypes.remove(0);       // 按索引删除

        // 7. 遍历列表
        for (String type : movieTypes) {
            System.out.println(type);
        }
    }
}
