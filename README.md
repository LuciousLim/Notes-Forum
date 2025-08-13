# LL笔记 (LL Notes)

LL笔记是一个面向程序员的在线笔记分享和学习平台，旨在为程序员提供一个高效的知识分享和交流空间。

## 项目特点

- 📝 笔记编写
- 🔍 笔记搜索功能
- 👥 用户互动和社交功能
- 🔔 实时消息通知系统

## 技术栈

**后端技术**

- 核心框架：Spring Boot 2.7.18
- 安全框架：Spring Security
- 持久层：MyBatis
- 数据库：MySQL 8.0
- 缓存：Redis  5.0.14.1
- 消息队列：Kafka 2.8.1
- 搜索引擎：Elasticsearch 7.17.13
- 消息推送：WebSocket
- 文件存储：本地文件系统
- 日志系统：Log4j2
- 测试框架：JUnit
- 模板引擎：Thymeleaf
- Markdown：Flexmark
- 工具库：Hutool

**前端技术**

- 构建工具：Vite
- 框架：React + TypeScript
- 路由管理：React Router DOM
- 状态管理：Redux Toolkit
- UI 库：Ant Design
- 样式：TailwindCSS
- HTTP 客户端：Axios
- WebSocket 客户端：原生 WebSoket


## 快速开始

### 环境要求
- Node.js 16+
- JDK 17+
- MySQL 8.0+
- Maven 3.8+
- 


## 主要功能

### 1. 用户系统
- 用户注册和登录
- 个人信息管理
- 用户主页

### 2. 笔记管理
- 创建和编辑笔记
- 笔记分类和标签
- 笔记收藏
- 笔记搜索

### 3. 社交功能
- 笔记评论
- 点赞功能
- 消息通知

## 项目结构

```
├── backend/                # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/kama/notes/
│   │   │   │       ├── annotation/   # 自定义注解
│   │   │   │       ├── aspect/       # AOP切面
│   │   │   │       ├── config/       # 配置类
│   │   │   │       ├── controller/   # 控制器
│   │   │   │       ├── exception/    # 异常处理
│   │   │   │       ├── filter/       # 过滤器
│   │   │   │       ├── interceptor/  # 拦截器
│   │   │   │       ├── mapper/       # 数据访问层
│   │   │   │       ├── model/        # 数据模型
│   │   │   │       ├── scope/        # 作用域数据
│   │   │   │       ├── service/      # 业务逻辑层
│   │   │   │       ├── task/         # 定时任务
│   │   │   │       └── utils/        # 工具类
│   │   │   └── resources/
│   │   │       ├── mapper/          # MyBatis映射文件
│   │   │       └── application.yml  # 配置文件
│   │   └── test/                    # 测试代码
│   └── pom.xml                      # 项目依赖管理
```


