package win.nelson.githubDailyHotSpots.pipeline

import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import us.codecraft.webmagic.ResultItems
import us.codecraft.webmagic.Task
import us.codecraft.webmagic.pipeline.Pipeline
import win.nelson.githubDailyHotSpots.config.MailConfig
import win.nelson.githubDailyHotSpots.data.ProjectData


@Component
class MailPipeline(
    val mailConfig: MailConfig,
    val mailSender: JavaMailSender,
    val templateEngine: TemplateEngine
    ): Pipeline {
    private val logger = LoggerFactory.getLogger(MailPipeline::class.java)


    /**
     * 处理抽取的结果
     *
     * @param resultItems resultItems
     * @param task task
     */
    override fun process(resultItems: ResultItems?, task: Task?) {
        val projectDataMutableList = resultItems?.get<MutableList<ProjectData>>("projects")
        if (projectDataMutableList == null || projectDataMutableList.isEmpty()) {
            logger.error("没有数据可以发邮件！！！")
            return
        }
        sendEmailWithTemplate(mailConfig, projectDataMutableList)
    }

    private fun sendEmailWithTemplate(mailConfig: MailConfig, projects: List<ProjectData>) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")

        helper.setTo(mailConfig.to)
        helper.setSubject(mailConfig.subject)
        helper.setFrom(mailConfig.from)

        val context = Context()
        context.setVariable("projects", projects)

        val htmlContent = templateEngine.process("emailTemplate", context)
        helper.setText(htmlContent, true)

        mailSender.send(message)
    }

}