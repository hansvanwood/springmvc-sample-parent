package com.example.request;

import lombok.Data;

/**
 * 电影列表查询请求参数
 */
@Data
public class MovieListRequest {

    /**
     * 关键词，模糊搜索电影名/别名/演员/导演
     */
    private String keyword;

    /**
     * 电影类型，如：剧情
     */
    private String type;

    /**
     * 语言，如：英语
     */
    private String language;

    /**
     * 制片国家/地区，如：中国
     */
    private String region;

    /**
     * 上映年份
     */
    private Integer year;

    /**
     * 最短时长（分钟）
     */
    private Integer minMinutes;

    /**
     * 最长时长（分钟）
     */
    private Integer maxMinutes;

    /**
     * 排序字段，默认 douban_score
     */
    private String sortField;

    /**
     * 排序方向：asc / desc，默认 desc
     */
    private String sortOrder;

    /**
     * 页码（必传）
     */
    private Integer pageNum;

    /**
     * 每页条数（必传）
     */
    private Integer pageSize;
}
