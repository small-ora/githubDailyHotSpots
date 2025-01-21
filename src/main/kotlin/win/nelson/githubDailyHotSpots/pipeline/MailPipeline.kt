package win.nelson.githubDailyHotSpots.pipeline

import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline
import win.nelson.githubDailyHotSpots.config.MailConfig
import win.nelson.githubDailyHotSpots.data.ProjectData

class MailPipeline(val mailConfig: MailConfig): Pipeline {


    /**
     * 处理抽取的结果
     *
     * @param resultItems resultItems
     * @param task task
     */
    override fun process(resultItems: ResultItems?, task: Task?) {
        val projectDataMutableList = resultItems?.get<MutableList<ProjectData>>("projects")
        MailAccount.sendMail(mailConfig, projectDataMutableList)

    }

}