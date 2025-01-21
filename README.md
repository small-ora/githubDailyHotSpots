# GitHub Daily Hot Spots

## 项目概述
该项目是一个使用Kotlin和Spring Boot框架开发的应用程序，每天定时获取GitHub上trending的项目，并使用大模型进行归纳总结，然后发送到指定邮箱。

感谢 [Kotlin](https://kotlinlang.org/) 和 [IntelliJ IDEA](https://www.jetbrains.com/idea/) 对本项目的贡献。
感谢 [通义零码（TONGYI Lingma）](https://www.aliyun.com/product/tongyi) 对本项目的贡献。

## 环境配置
- Java 21
- Kotlin
- Gradle 8.5
- Docker
- Docker Compose

## 依赖项
- Spring Boot
- Gradle
- OpenJDK 21

## 运行方式

### 使用Docker运行
1. 构建Docker镜像
   ```bash
   docker build -t githubdailyhotspots .
   ```
2. 运行Docker容器
   ```bash
   docker run -e LLM_KEY=your_llm_key -e LLM_MODEL=your_llm_model -e LLM_URL=your_llm_url -e MAIL_HOST=your_mail_host -e MAIL_PORT=your_mail_port -e MAIL_USERNAME=your_mail_username -e MAIL_PASSWORD=your_mail_password githubdailyhotspots
   ```

### 使用Docker Compose运行
1. 修改`docker-compose.yml`文件中的环境变量
2. 启动服务
   ```bash
   docker-compose up
   ```

## 使用方法
1. 配置环境变量（LLM_KEY, LLM_MODEL, LLM_URL, MAIL_HOST, MAIL_PORT, MAIL_USERNAME, MAIL_PASSWORD）
2. 运行项目
3. 每天定时获取GitHub上trending的项目，并使用大模型进行归纳总结，然后发送到指定邮箱

## 代码结构
- `src/main/kotlin/win/nelson/githubDailyHotSpots/task/CronTask.kt`: 定时任务类，负责获取GitHub trending项目并进行处理
- `Dockerfile`: Docker构建文件
- `docker-compose.yml`: Docker Compose配置文件

## 贡献
欢迎任何形式的贡献，请参考[贡献指南](CONTRIBUTING.md)

## 许可证
本项目采用[MIT许可证](LICENSE)