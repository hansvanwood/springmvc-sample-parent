package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应状态码枚举
 * <p>
 * 将所有可能的业务状态码集中管理，避免魔法数字散落在项目代码各处。
 * 遵循 HTTP 语义：
 * 2xx → 成功
 * 4xx → 客户端错误（参数问题、资源不存在等）
 * 5xx → 服务器内部错误
 */
@Getter
@AllArgsConstructor  // Lombok：自动生成包含所有字段的构造方法，与枚举构造器配合使用
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