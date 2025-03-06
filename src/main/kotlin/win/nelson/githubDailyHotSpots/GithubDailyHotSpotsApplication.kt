package win.nelson.githubDailyHotSpots

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import win.nelson.githubDailyHotSpots.llm.LlmSummarize

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["win.nelson.githubDailyHotSpots.config"])
@EnableScheduling
@EntityScan("win.nelson.githubDailyHotSpots.data")
class GithubDailyHotSpotsApplication



fun main(args: Array<String>) {
	runApplication<GithubDailyHotSpotsApplication>(*args)
}
