pluginManagement {
	repositories {
		maven { url = uri("https://maven.aliyun.com/repository/public") }
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
		gradlePluginPortal()
	}
}
rootProject.name = "githubDailyHotSpots"
