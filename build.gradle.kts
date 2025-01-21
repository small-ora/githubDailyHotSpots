plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.7"
}

dependencyManagement {
	imports {
		mavenBom("cn.hutool:hutool-bom:5.8.26")
	}
}

group = "win.nelson"
version = "1.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenLocal()
	maven { url = uri("https://maven.aliyun.com/repository/public") }
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("us.codecraft:webmagic-core:1.0.1")
	implementation("cn.hutool:hutool-http")
	implementation("com.sun.mail:jakarta.mail:2.0.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// 配置 bootJar 任务，使其生成的 JAR 文件不包含版本号
tasks.bootJar {
	archiveVersion.set("")
}

// 添加一个任务来构建 Docker 镜像
tasks.register<Exec>("buildDockerImage") {
	dependsOn(tasks.build)
	commandLine("docker", "build", "-t", "github_daily_hotspots:${project.version}", ".")
}
