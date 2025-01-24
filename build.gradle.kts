plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.bmuschko.docker-remote-api") version "9.4.0" // 添加Docker插件
}

docker{
	url.set("tcp://192.168.31.193:2375")
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
	maven { url = uri("https://maven.aliyun.com/repository/spring") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("us.codecraft:webmagic-core:1.0.1")
	implementation("cn.hutool:hutool-http:5.8.26")
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
	archiveFileName.set("app.jar")
}


tasks.create("buildDockerImage", com.bmuschko.gradle.docker.tasks.image.DockerBuildImage::class) {
	doNotTrackState("禁用状态跟踪")
	inputDir.set(file("docker")) // 指向专用目录
	images.add("github_daily_hotspots:latest")


	// 添加依赖：先复制 JAR 到 docker 目录
	dependsOn(tasks.named("bootJar"))
	doFirst {
		copy {
			from("build/libs/app.jar")
			into("docker")
		}
	}
}

// 清理时删除临时文件
tasks.clean {
	doLast {
		delete("docker/app.jar")
	}
}