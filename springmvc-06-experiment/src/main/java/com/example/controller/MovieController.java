package com.example.controller;

import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.common.exception.BusinessException;
import com.example.dto.request.MovieCreateRequest;
import com.example.dto.request.MovieUpdateRequest;
import com.example.dto.response.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 电影管理 API 接口
 * <p>
 * 讲师提示：
 * 1. @RestController = @Controller + @ResponseBody，保证返回数据格式是 JSON。
 * 2. @RequestMapping("/movies") 定义了类的公共基础路径，符合 RESTful 中采用资源复数名词的规范。
 */
@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    /**
     * 接口 1 (GET)：条件查询电影列表
     * 场景演示：接收 URL 中 ? 号后面的参数 (Query 传参)
     */
    @GetMapping
    public Result<List<MovieResponse>> searchMovies(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String tag) {

        log.info(">>> 收到查询请求，检索条件 - 类型: {}, 标签: {}", type, tag);

        // 这里仅做数据模拟，后续课程我们会在此调用 Service 查数据库
        List<MovieResponse> mockData = Arrays.asList(
                MovieResponse.builder().movieId(24869902).movieName("疯狂的外星人").type("喜剧/科幻").doubanScore(6.9).build(),
                MovieResponse.builder().movieId(25895315).movieName("战狼2").type("动作/战争").doubanScore(7.1).build()
        );

        return Result.success(mockData);
    }

    /**
     * 接口 2 (POST)：新增电影
     * 场景演示：接收 JSON 格式的请求体 (Body 传参)
     * 重点：必须要加 @Validated 才会触发我们在 DTO 中写的 @NotBlank 等校验逻辑！
     */
    @PostMapping
    public Result<Integer> createMovie(@Validated @RequestBody MovieCreateRequest request) {

        log.info(">>> 收到新增电影请求: {}", request.toString());

        // 模拟逻辑处理...
        Integer newId = 36248602; // 假装插入数据库后得到了主键ID

        return Result.success(newId);
    }

    /**
     * 接口 3 (PUT)：修改电影评分与时长
     * 场景演示：混合使用路径传参 (Path) 和 实体传参 (Body)
     */
    @PutMapping("/{id}")
    public Result<Void> updateMovie(
            @PathVariable("id") Integer id,
            @Validated @RequestBody MovieUpdateRequest request) {

        log.info(">>> 收到修改电影请求，要修改的电影ID: {}", id);
        log.info(">>> 新的评分: {}, 新的时长: {}", request.getDoubanScore(), request.getMinutes());

        // 模拟业务异常场景（比如要修改的电影不存在）
        if (id < 0) {
            // 主动抛出自定义异常，它会被 GlobalExceptionHandler 捕获并妥善返回给前端
            throw new BusinessException(ResultCode.NOT_FOUND, "要修改的电影ID不存在：" + id);
        }

        // 模拟修改成功
        return Result.success();
    }
}