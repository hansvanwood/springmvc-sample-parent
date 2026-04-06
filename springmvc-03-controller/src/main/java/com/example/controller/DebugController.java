package com.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/debug")
public class DebugController {

    /**
     * 打印本次请求的完整信息（调试用）
     */
    @GetMapping("/request-info")
    public Map<String, String> getRequestInfo(HttpServletRequest request) {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("请求方法", request.getMethod());                    // GET
        info.put("请求URL", request.getRequestURL().toString());     // http://localhost:8080/api/debug/request-info
        info.put("请求URI", request.getRequestURI());                // /api/debug/request-info
        info.put("查询参数", request.getQueryString());               // name=test&age=18
        info.put("客户端IP", request.getRemoteAddr());               // 127.0.0.1
        info.put("Content-Type", request.getContentType());          // null（GET请求通常没有）
        info.put("User-Agent", request.getHeader("User-Agent"));     // PostmanRuntime/7.x
        return info;
    }
}