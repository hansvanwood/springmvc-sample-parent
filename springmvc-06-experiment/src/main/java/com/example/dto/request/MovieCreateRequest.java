package com.example.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * 新增电影请求 DTO
 * 讲师提示：使用 validation 校验注解，可以把脏数据挡在 Controller 外面，
 * 避免脏数据进入 Service 和数据库，这是非常优雅的做法！
 */
@Data
public class MovieCreateRequest {

    @NotBlank(message = "电影名称不能为空")
    private String movieName;

    @NotBlank(message = "导演不能为空")
    private String directors;

    @NotBlank(message = "电影类型不能为空")
    private String type;

    @DecimalMin(value = "0.0", message = "评分不能低于0分")
    @DecimalMax(value = "10.0", message = "评分不能超过10分")
    private Double doubanScore;

    @NotNull(message = "电影时长不能为空")
    @Min(value = 1, message = "时长至少为1分钟")
    private Integer minutes;

    // 前端传入格式必须为 yyyy-MM-dd
    private LocalDate releaseDate;
}