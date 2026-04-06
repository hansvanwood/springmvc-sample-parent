package com.example.controller;

import com.example.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        log.info("【5. Controller】开始处理业务逻辑，查询 ID 为 {} 的用户", id);

        // 模拟 Service 调用
        return new UserDTO(id, "Java21_Expert", "dev@example.com");
    }
}