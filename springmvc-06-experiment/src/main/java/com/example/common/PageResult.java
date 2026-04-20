package com.example.common;

import lombok.Data;

import java.util.List;

/**
 * 统一分页返回体
 * 在 Result 的基础上增加了分页信息字段
 */
@Data
public class PageResult<T> {

    private int code;
    private String message;
    private long total;       // 总条数
    private int pageNum;      // 当前页码
    private int pageSize;     // 每页条数
    private List<T> data;     // 数据列表

    /**
     * 分页成功返回
     */
    public static <T> PageResult<T> success(List<T> data, long total, int pageNum, int pageSize) {
        PageResult<T> result = new PageResult<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        result.setTotal(total);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        return result;
    }
}