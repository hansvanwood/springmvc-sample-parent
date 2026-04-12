# Spring MVC 开发实战示例 (Spring MVC Sample Parent)

本项目是 Spring MVC 系列进阶教程的配套源代码工程。通过本项目的学习与实践，您将掌握使用 Spring Boot 4 和 Java 21 进行现代化
Web 接口开发的各项核心技能，包括 RESTful API 设计、参数接收与绑定、数据安全校验、统一响应体封装，以及深入理解 Spring MVC
核心架构原理。

## 🛠 开发环境配置

- **JDK版本**：Java 21
- **构建工具**：Maven 3.x
- **核心框架**：Spring Boot 4.0.3 (内置 Spring MVC)
- **开发工具**：IntelliJ IDEA

## 📚 配套教程大纲

本项目代码紧密围绕以下四篇核心实战教程展开：

| 教程文档名称                            | 核心知识点与实践内容                                                                                                                                                                                                                   |
|:----------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **1.Controller接口开发入门.md**         | • 掌握前后端通信原理与 HTTP 协议基础（请求/响应结构、状态码）<br>• 使用 IDEA 创建 Spring Boot 4.x 工程<br>• 第一个 `@RestController` 与 JSON 响应实战<br>• Spring Boot 静态资源接管与前后端解耦演示<br>• 配置 `spring-boot-devtools` 开启项目热部署模式                                       |
| **2.RESTful接口设计与常见写法.md**         | • RESTful 架构风格概览与接口设计最佳实践<br>• 精通 `@PathVariable` (路径参数)、`@RequestParam` (查询参数)、`@RequestBody` (JSON体参数) 等多样化参数接收机制<br>• 基于 `MultipartFile` 实现文件上传及资源导出下载功能<br>• `@RequestHeader`、`HttpServletRequest`、`ResponseEntity` 进阶实战 |
| **3.数据处理与接口规范.md**                | • 使用 Java `List`、`Map` 和 `Stream API` 获取及处理业务数据<br>• 深刻理解 Java **泛型**在接口封装中的价值<br>• 设计企业级统一响应结构 `Result<T>` 及分页响应 `PageResult<T>`<br>• 整合 **Jakarta Validation** 实现基于 `@Valid` 的模型参数严苛安全校验                                   |
| **番外篇1：MVC 架构详解与自定义Converter.md** | • MVC 分层思想与 `DispatcherServlet` 请求完整流转链路剖析<br>• 拦截器 (Interceptor) 与过滤器 (Filter) 的工作机制差异及实战应用<br>• 探究使用 Java 21 `Record` 类型简化 DTO 开发<br>• 注册自定义 `Converter` 自动化繁杂字符串到复杂 Java 对象的映射                                            |

## 🚀 工程结构说明

项目采用 Maven 多模块结构组织，每个子模块对应教程中不同的实战知识领域，相互隔离，便于分阶段独立学习：

- `springmvc-01-quickstart/`：快速入门模块。包含第一个 Controller 搭建以及引入静态页面的交互演示。
- `springmvc-02-mvc/`：架构演示模块。重点演示 Filter 过滤器、Interceptor 拦截器的埋点追踪和请求路径排查技巧。
- `springmvc-03-controller/`：参数绑定模块。囊括教程中所有常见参数绑定注解的最佳使用姿势示例。
- `springmvc-04-validation/`：数据校验模块。包含 DTO 的格式约束与基于 `@ExceptionHandler` 处理校验异常的方案。
- `springmvc-05-response/`：响应数据模块。实战通用泛型响应体（单对象和分页模型），以及灵活调用的 ResponseEntity。
- `springmvc-10-practice/`：综合实战演练模块。将上述零碎知识点整合应用，实战更接近真实业务场景的 API 开发。

## 📖 启动与体验指南

1. 将本仓源码克隆或下载到本地，使用 IntelliJ IDEA 导入 `springmvc-sample-parent` 为 Maven 工程。
2. 确保在 IDEA 设置面板中将 **Project SDK** 选定为 **Java 21**。
3. 待 Maven 完全解析并同步本工程所有的相关依赖包。
4. 展开所需要学习的特定子模块 (如 `springmvc-01-quickstart`)。
5. 在 `src/main/java` 目录下找到带有 `@SpringBootApplication` 注解的启动类 (如 `App.java`)，右键点击 `Run` 执行。
6. 服务器启动成功后，即可根据教程中的示例，使用浏览器或 API 测试工具 (如 Postman) 对 `http://localhost:8080` 发起端到端测试。

---
> 💡 **学习小贴士**：为了更好地掌握 Spring MVC 的执行流程，强烈建议您在阅读 _"番外篇1：MVC 架构详解"_ 时，打开本项目的相关模块开启
> Debug 模式，并在 `DispatcherServlet#doDispatch` 方法上打下断点，亲身体验一个请求在框架底层流转的奇妙之旅。
