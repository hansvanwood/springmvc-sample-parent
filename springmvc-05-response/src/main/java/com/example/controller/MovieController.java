package com.example.controller;

import com.example.common.PageResult;
import com.example.common.Result;
import com.example.request.MovieListRequest;
import com.example.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * 单数据返回：Result<String>
     */
    @GetMapping("/{movieId}")
    public Result<String> getMovieDetail(@PathVariable Integer movieId) {
        return Result.success("查询电影详情，ID=" + movieId);
    }

    /**
     * 列表返回：Result<List<String>>
     */
    @GetMapping("/types")
    public Result<List<String>> getTypes() {
        return Result.success(List.of("动作", "喜剧", "爱情", "科幻", "悬疑", "犯罪", "动画", "家庭", "奇幻", "冒险"));
    }

    /**
     * 分页返回：PageResult<List<String>>，实际开发中，分页查询结果通常是POJO对象列表，而不是字符串列表，这里只是为了演示而使用字符串列表
     */
    @PostMapping("/list")
    public PageResult<String> getMovieList(@Valid @RequestBody MovieListRequest request) {
        return PageResult.success(List.of("Movie 1", "Movie 2", "Movie 3"),
                3, request.getPageNum(), request.getPageSize());
    }

    // ❌ 不推荐：每个方法都 try-catch
//    @GetMapping("/{movieId}")
//    public Result<String> getMovieDetail(@PathVariable Integer movieId) {
//        try {
//            String detail = "Movie detail for ID " + movieId;
//            return Result.success(detail);
//        } catch (BusinessException e) {
//            return Result.fail(e.getResultCode());
//        } catch (Exception e) {
//            return Result.fail(ResultCode.INTERNAL_ERROR);
//        }
//    }
}