package win.nelson.githubDailyHotSpots

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import win.nelson.githubDailyHotSpots.llm.LlmSummarize

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["win.nelson.githubDailyHotSpots.config"])
@EnableScheduling
class GithubDailyHotSpotsApplication



fun main(args: Array<String>) {
	val runApplication = runApplication<GithubDailyHotSpotsApplication>(*args)
	val llmSummarize = runApplication.getBean(LlmSummarize::class.java)

}
