package com.example.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/download")
public class FileDownloadController {

    /**
     * 下载电影海报图片
     * URL 示例：GET /api/download/poster?filename=abc123.jpg
     */
    @GetMapping("/poster")
    public ResponseEntity<Resource> downloadPoster(@RequestParam String filename) {
        // 1. 定位文件
        String filePath = System.getProperty("user.dir") + "/uploads/poster/" + filename;
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 2. 构建资源对象
        Resource resource = new FileSystemResource(file);

        // 3. 设置响应头，告诉浏览器以附件形式下载
        String encodedFilename = URLEncoder.encode(file.getName(), StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}