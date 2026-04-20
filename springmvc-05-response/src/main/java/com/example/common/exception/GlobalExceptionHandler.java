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
 *
 * @RestControllerAdvice 是 @ControllerAdvice + @ResponseBody 的组合注解。
 * 它会拦截所有 Controller 中抛出的异常，并将其统一转换为 Result<T> 格式返回给前端。
 * 这样我们就不需要在每个接口中都写 try-catch，大大简化了代码。
 *
 * 异常捕获优先级：精确匹配 > 父类匹配
 * 例如 MethodArgumentNotValidException 会先被自己的 handler 捕获，不会落到 Exception 兜底。
 */
@Slf4j  // Lombok：自动注入 Logger 对象 log，相当于 private static final Logger log = LoggerFactory.getLogger(...)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验异常（@RequestBody 中的字段校验失败时触发）
     *
     * 触发场景：Controller 方法参数使用了 @Valid / @Validated，
     * 且请求体中的字段不符合 @NotBlank / @Min / @Max 等注解约束时。
     *
     * @param e 参数校验异常对象（包含所有校验失败的字段信息）
     * @return 包含具体错误字段提示的失败响应
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        // 遍历所有校验失败的字段，拼接成 "字段名: 错误原因; ..." 的格式
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        log.warn("【参数校验失败】{}", errorMsg);
        return Result.fail(ResultCode.BAD_REQUEST.getCode(), errorMsg);
    }

    /**
     * 处理 JSON 请求体不可读异常（请求体格式错误时触发）
     *
     * 触发场景：
     *   1. 请求体不是合法 JSON 格式
     *   2. 字段类型不匹配（如 score 传了字符串 "abc"）
     *   3. Content-Type 未设置为 application/json
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("【请求体解析失败】{}", e.getMessage());
        return Result.fail(ResultCode.BAD_REQUEST.getCode(), "请求体格式错误，请检查 JSON 格式及字段类型");
    }

    /**
     * 处理约束校验异常（@RequestParam / @PathVariable 校验失败时触发）
     *
     * 注意：@RequestParam 和 @PathVariable 的校验需要在 Controller 类上加 @Validated，
     * 失败时抛出的是 ConstraintViolationException，与 @RequestBody 校验抛出的异常不同！
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> handleConstraintViolation(ConstraintViolationException e) {
        String errorMsg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        log.warn("【约束校验失败】{}", errorMsg);
        return Result.fail(ResultCode.BAD_REQUEST.getCode(), errorMsg);
    }

    /**
     * 处理业务异常（Controller/Service 中主动抛出的 BusinessException）
     *
     * 触发场景：
     *   throw new BusinessException(ResultCode.NOT_FOUND, "电影 ID=xxx 不存在");
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("【业务异常】{}", e.getMessage());
        return Result.fail(e.getResultCode().getCode(), e.getMessage());
    }

    /**
     * 兜底异常处理（捕获所有未被上方 handler 处理的异常）
     *
     * 这是最后一道防线。任何没有被精确捕获的异常都会落到这里。
     * 注意：一定要打印完整堆栈（log.error），方便排查问题！
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("【兜底异常】真实异常类型: {}", e.getClass().getName());
        log.error("【兜底异常】堆栈信息:", e);
        return Result.fail(ResultCode.INTERNAL_ERROR);
    }
}