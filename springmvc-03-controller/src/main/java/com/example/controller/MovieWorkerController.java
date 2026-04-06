package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workers")
public class MovieWorkerController {

    /**
     * 工作者详情
     * URL 示例：GET /api/workers/2001
     */
    @GetMapping("/{workerId}")
    public String getWorkerDetail(@PathVariable Integer workerId) {
        return "根据 workerId 查询工作者详情: " + workerId;
    }


}