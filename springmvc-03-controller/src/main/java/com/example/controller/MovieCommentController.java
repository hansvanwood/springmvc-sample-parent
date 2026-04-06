package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovieCommentController {

    /**
     * 查询某部电影的评论列表
     * URL 示例：GET /api/movies/34780988/comments?pageNum=2&pageSize=10
     * <p>
     * 说明：movieId 来自路径参数，pageNum 和 pageSize 来自查询参数
     */
    @GetMapping("/movies/{movieId}/comments")
    public String getMovieComments(@PathVariable Integer movieId,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "20") Integer pageSize) {
        // ... 调用 Service 查询
        return "查询某部电影的评论列表，movieId=" + movieId + ", pageNum=" + pageNum + ", pageSize=" + pageSize;
    }

}