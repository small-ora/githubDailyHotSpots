# 使用官方的 Kotlin 和 Gradle 镜像作为构建环境
FROM gradle:8.12.0-jdk21 AS build
# 设置工作目录
WORKDIR /app
# 复制项目文件到工作目录
COPY --chown=gradle:gradle . .
# 构建项目并生成可执行的 JAR 文件
RUN gradle build --no-daemon

# 使用官方的 OpenJDK 镜像作为运行环境
FROM openjdk:21-jdk
# 设置工作目录
WORKDIR /app
# 从构建阶段复制生成的 JAR 文件
COPY --from=build /app/build/libs/githubDailyHotSpots.jar /app/githubDailyHotSpots.jar
# 运行应用
CMD ["java", "-jar", "githubDailyHotSpots.jar"]