package com.example.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    /**
     * 上传电影海报图片
     * <p>
     * 前端请求方式：
     * POST /api/upload/poster
     * Content-Type: multipart/form-data
     * 表单字段名：file
     */
    @PostMapping("/poster")
    public String uploadPoster(@RequestParam("file") MultipartFile file) throws IOException {
        // 1. 校验文件是否为空
        if (file.isEmpty()) {
            return "上传文件不能为空";
        }

        // 2. 获取原始文件名并生成唯一文件名，防止重名覆盖
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + suffix;

        // 3. 指定保存目录并确保目录存在
        String uploadDir = System.getProperty("user.dir") + "/uploads/poster/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 4. 将文件保存到指定路径
        file.transferTo(new File(uploadDir + newFilename));

        // 5. 返回文件的访问路径
        return "上传的文件路径为：/uploads/poster/" + newFilename;
    }

    /**
     * 批量上传文件
     * 前端使用同一个字段名 files 传递多个文件
     */
    @PostMapping("/batch")
    public String uploadBatch(@RequestParam("files") MultipartFile[] files) throws IOException {
        List<String> paths = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String newFilename = UUID.randomUUID().toString() + fileSuffix;
                file.transferTo(new File("/uploads/" + newFilename));
                paths.add("/uploads/" + newFilename);
            }
        }
        return "上传的文件路径为：" + paths;
    }
}