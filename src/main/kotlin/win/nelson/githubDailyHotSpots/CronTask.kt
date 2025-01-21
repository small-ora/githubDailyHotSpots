package win.nelson.githubDailyHotSpots

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Spider
import win.nelson.githubDailyHotSpots.config.MailConfig
import win.nelson.githubDailyHotSpots.config.SchedulerConfig
import win.nelson.githubDailyHotSpots.llm.LlmSummarize
import win.nelson.githubDailyHotSpots.pipeline.MailPipeline
import win.nelson.githubDailyHotSpots.process.TrendingProcess

@Component
class CronTask(
    val scheduler: SchedulerConfig,
    val llmSummarize: LlmSummarize,
    val mailConfig: MailConfig) {

    @Scheduled(cron = "\${scheduler.cronExpression}")
    fun cronTask() {
        Spider.create(TrendingProcess(llmSummarize))
            .addUrl("https://github.com/trending")
            .addPipeline(MailPipeline(mailConfig))
            .run()
    }
}