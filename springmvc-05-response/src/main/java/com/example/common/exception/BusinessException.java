package com.example.common.exception;

import com.example.common.ResultCode;
import lombok.Getter;

/**
 * 业务异常
 * 用于 Service 层主动抛出，表示业务逻辑错误（而非系统错误）
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误状态码
     */
    private final ResultCode resultCode;

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public BusinessException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}