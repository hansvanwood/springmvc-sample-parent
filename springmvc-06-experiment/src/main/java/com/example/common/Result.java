package com.example.common;

import lombok.Data;

/**
 * 统一接口响应体
 * <p>
 * 所有接口的返回值都统一封装为此格式：
 * {
 * "code": 200,
 * "message": "操作成功",
 * "data": { ... }
 * }
 * <p>
 * 使用泛型 <T> 使 data 字段可以承载任意类型的数据
 */
@Data
public class Result<T> {

    /**
     * HTTP 业务状态码（非 HTTP 状态码，由我们自己定义）
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据，类型由泛型 T 决定
     */
    private T data;

    /**
     * 成功返回（不带数据）
     *
     * @param <T> 数据类型
     * @return 封装后的成功响应
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(null);
        return result;
    }

    /**
     * 成功返回（带数据）
     *
     * @param data 业务数据
     * @param <T>  数据类型
     * @return 封装后的成功响应
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败返回（使用预定义的 ResultCode 枚举）
     *
     * @param resultCode 枚举中的状态码
     * @param <T>        数据类型（失败时 data 为 null）
     * @return 封装后的失败响应
     */
    public static <T> Result<T> fail(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    /**
     * 失败返回（自定义 code 和 message，用于字段校验错误等场景）
     *
     * @param code    自定义状态码
     * @param message 自定义错误信息
     * @param <T>     数据类型
     * @return 封装后的失败响应
     */
    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}