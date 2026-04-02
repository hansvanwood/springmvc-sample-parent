package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringMVC 常见功能演示 - 启动类
 */
@SpringBootApplication
public class App01 {

    public static void main(String[] args) {
        SpringApplication.run(App01.class, args);
        System.out.println("============================================");
        System.out.println("  springmvc-01-quickstart 启动成功!");
        System.out.println("  API地址: http://localhost:8080/hello");
        System.out.println("  首页: http://localhost:8080/index.html");
        System.out.println("  电影: http://localhost:8080/movie.html");
        System.out.println("  关于: http://localhost:8080/about.html");
        System.out.println("============================================");
    }
}
