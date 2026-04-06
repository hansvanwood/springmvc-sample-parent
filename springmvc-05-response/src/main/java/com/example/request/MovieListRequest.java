package com.example.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 电影列表查询请求参数
 */
@Data
public class MovieListRequest {

    /**
     * 关键词（可选，不做校验）
     */
    private String keyword;

    /**
     * 电影类型（可选）
     */
    private String type;

    /**
     * 语言（可选）
     */
    private String language;

    /**
     * 制片国家/地区（可选）
     */
    private String region;

    /**
     * 上映年份（可选）
     */
    private Integer year;

    /**
     * 最短时长
     */
    @Min(value = 0, message = "最短时长不能为负数")
    private Integer minMinutes;

    /**
     * 最长时长
     */
    @Min(value = 0, message = "最长时长不能为负数")
    private Integer maxMinutes;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序方向
     */
    private String sortOrder;

    /**
     * 页码（必传）
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    private Integer pageNum = 1;

    /**
     * 每页条数（必传）
     */
    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小为1")
    @Max(value = 200, message = "每页条数最大为200")
    private Integer pageSize = 20;
}