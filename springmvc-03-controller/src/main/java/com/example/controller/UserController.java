package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * 获取当前用户信息
     * 前端在请求头中携带 Token：Authorization: Bearer eyJhbGciOi...
     */
    @GetMapping("/profile")
    public String getUserProfile(@RequestHeader("Authorization") String authHeader) {
        // 从 Authorization 头中提取 Token
        String token = authHeader.replace("Bearer ", "");
        // ... 根据 Token 解析用户信息
        return "用户信息，Token: " + token;
    }

    /**
     * 获取客户端类型（可选的请求头）
     */
    @GetMapping("/client-info")
    public String getClientInfo(@RequestHeader(value = "User-Agent", required = false) String userAgent) {
        return "客户端标识: " + userAgent;
    }
}