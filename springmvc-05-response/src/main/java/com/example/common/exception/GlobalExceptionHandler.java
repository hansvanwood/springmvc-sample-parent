package com.example.common.exception;

import com.example.common.Result;
import com.example.common.ResultCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * <p>
 * 工作原理：
 * 1. Controller 方法抛出异常
 * 2. Spring 拦截异常，匹配 @ExceptionHandler 标注的方法
 * 3. 按异常类型精确匹配，返回统一格式的 JSON 响应
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 @RequestBody 参数校验异常
     * 当 @Valid 校验失败时，Spring 抛出此异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        // 使用 Stream 拼接所有字段的错误信息
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.warn("参数校验失败: {}", errorMsg);
        return Result.fail(ResultCode.BAD_REQUEST.getCode(), errorMsg);
    }

    /**
     * 处理 JSON 请求体格式错误
     * 如前端发送的 JSON 格式不合法、字段类型不匹配等
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("请求体不可读: {}", e.getMessage());
        return Result.fail(ResultCode.BAD_REQUEST.getCode(), "参数格式解析失败");
    }

    /**
     * 处理 @RequestParam / @PathVariable 的约束校验异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolation(ConstraintViolationException e) {
        String errorMsg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        log.warn("约束校验失败: {}", errorMsg);
        return Result.fail(ResultCode.BAD_REQUEST.getCode(), errorMsg);
    }

    /**
     * 处理自定义业务异常
     * 由业务代码主动抛出，例如"电影不存在"
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("业务异常: {}", e.getMessage());
        return Result.fail(e.getResultCode().getCode(), e.getMessage());
    }

    /**
     * 兜底异常处理
     * 捕获所有未被上面方法处理的异常，避免直接向前端暴露堆栈信息
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("触发兜底异常，真实类型是: {}", e.getClass().getName());
        log.error("异常堆栈:", e);
        return Result.fail(ResultCode.INTERNAL_ERROR);
    }
}