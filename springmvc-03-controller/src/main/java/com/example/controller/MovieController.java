package com.example.controller;

import com.example.request.MovieListRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    /**
     * 电影详情
     * URL 示例：GET /api/movies/1001
     * 其中 1001 会被自动绑定到 movieId 参数上
     */
    @GetMapping("/{movieId}")
    public String getMovieDetail(@PathVariable Integer movieId) {
        return "根据 movieId 查询电影详情: " + movieId;
    }


    /**
     * @RequestParam 常用属性演示
     */
    @GetMapping("/search")
    public String searchMovies(// 必传参数：keyword 必须提供，否则返回 400
                               @RequestParam String keyword,

                               // 可选参数：设置 required = false，不传时值为 null
                               @RequestParam(required = false) String type,

                               // 带默认值的参数：不传时使用默认值 "1"
                               @RequestParam(defaultValue = "1") Integer pageNum,

                               // 参数名映射：前端传 page_size，后端用 pageSize 接收
                               @RequestParam(value = "page_size", defaultValue = "20") Integer pageSize) {

        // keyword、type、pageNum、pageSize 均可直接使用
        return "根据 keyword 查询电影列表，keyword=" + keyword + ", type=" + type + ", pageNum=" + pageNum + ", pageSize=" + pageSize;
    }

    /**
     * 电影概览列表（分页+筛选+排序）
     * 使用 POST + @RequestBody 接收复杂的查询条件
     */
    @PostMapping("/list")
    public String getMovieList(@RequestBody MovieListRequest request) {
        return "查询电影列表，参数=" + request.toString();
    }



}