package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class MovieController {

    @GetMapping("/movies")
    @ResponseBody
    public Map<Integer, String> listMovies() {
        Map<Integer, String> movies = new LinkedHashMap<>();
        movies.put(1, "流浪地球3");          // 备受期待的国产科幻巨作
        movies.put(2, "哪吒之魔童闹海");     // 继《魔童降世》后的神话史诗续集
        movies.put(3, "长安三万里：杜甫篇");  // 追光动画“新文化”系列新作
        movies.put(4, "红海行动2：海龙行动"); // 动作战争大片续作
        movies.put(5, "雄狮少年2");          // 现实主义热血动画续篇
        return movies;
    }
}
