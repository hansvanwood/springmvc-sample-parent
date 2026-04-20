package com.example.common.exception;

import com.example.common.ResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * 为什么需要自定义异常？
 * Java 内置的异常（如 RuntimeException）无法携带我们定义的 ResultCode。
 * 通过自定义异常，我们可以在业务代码中主动抛出语义清晰地异常，
 * 然后由 GlobalExceptionHandler 统一捕获并转换为标准的 Result 格式返回给前端。
 *
 * 使用示例：
 *   throw new BusinessException(ResultCode.NOT_FOUND, "电影 ID=" + movieId + " 不存在");
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误状态码（携带 ResultCode，方便异常处理器提取 code 和 message）
     */
    private final ResultCode resultCode;

    /**
     * 构造方法一：使用 ResultCode 的默认 message
     *
     * @param resultCode 预定义状态码枚举
     */
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * 构造方法二：使用自定义的 message（更具体的错误描述）
     *
     * @param resultCode 预定义状态码枚举
     * @param message    自定义错误描述（会覆盖枚举默认 message）
     */
    public BusinessException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}