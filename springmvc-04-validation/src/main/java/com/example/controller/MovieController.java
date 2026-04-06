package com.example.controller;

import com.example.request.MovieListRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    /**
     * @Valid 配合 @RequestBody 校验 JSON 请求体
     * 如果校验失败，Spring 会自动抛出 MethodArgumentNotValidException
     */
    @PostMapping("/list")
    public String getMovieList(@Valid @RequestBody MovieListRequest request) {
        return "查询电影列表，参数=" + request.toString();
    }
}