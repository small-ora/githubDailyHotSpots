# 使用官方的 OpenJDK 镜像作为运行环境
FROM eclipse-temurin:21-jre-jammy
# 设置工作目录
WORKDIR /app
# 复制生成的 JAR 文件
COPY  app.jar /app/githubDailyHotSpots.jar
# 运行应用
CMD ["java", "-jar", "githubDailyHotSpots.jar"]