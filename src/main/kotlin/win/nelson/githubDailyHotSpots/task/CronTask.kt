package win.nelson.githubDailyHotSpots.task

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
    val trendingProcess: TrendingProcess,
    val mailPipeline: MailPipeline
) {

    @Scheduled(cron = "\${scheduler.cronExpression}")
    fun cronTask() {
        Spider.create(trendingProcess)
            .addUrl("https://github.com/trending")
            .addPipeline(mailPipeline)
            .run()
    }
}