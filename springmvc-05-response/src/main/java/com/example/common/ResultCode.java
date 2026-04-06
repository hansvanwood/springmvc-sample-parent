package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局状态码枚举
 * 统一管理所有的状态码和描述信息，避免硬编码
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    BAD_REQUEST(400, "请求参数错误"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_ERROR(500, "服务器内部错误");

    /**
     * 状态码
     */
    private final int code;
    /**
     * 状态描述
     */
    private final String message;
}