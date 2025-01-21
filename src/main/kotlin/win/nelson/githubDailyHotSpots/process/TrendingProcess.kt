package win.nelson.githubDailyHotSpots.process

import cn.hutool.core.codec.Base64
import cn.hutool.http.HttpUtil
import com.alibaba.fastjson2.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.processor.PageProcessor
import win.nelson.githubDailyHotSpots.data.ProjectData
import win.nelson.githubDailyHotSpots.data.RepositoryReadme
import win.nelson.githubDailyHotSpots.llm.LlmSummarize

@Component
class TrendingProcess(val llmSummarize: LlmSummarize) : PageProcessor {

    private val logger = LoggerFactory.getLogger(TrendingProcess::class.java)

    private val site = Site.me()
            .setDomain("github.com")
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36")
            .setRetryTimes(3)
            .setCycleRetryTimes(3)
            .setTimeOut(10000)
            .setCharset("utf-8")

    override fun process(page: Page?) {
        val all = page?.html?.xpath("//h2[@class='h3 lh-condensed']//a/@href")?.all()
        if (all.isNullOrEmpty()) {
            println("列表为空！！！")
            return
        }
        val projectDatas = mutableListOf<ProjectData>()
        for (item in all) {
            // 提取项目名称
            val strings = item.split("/").filter { it.isNotEmpty() }
            val owner = strings.first()
            val repo = strings.last()
            //获取项目默认分支
            val repositoryReadme = HttpUtil.get("https://api.github.com/repos/$owner/$repo/readme")
            val readmeInfo = JSONObject.parse(repositoryReadme).to(RepositoryReadme::class.java)
            if (readmeInfo == null){
                logger.warn("无法获取README文件信息")
            }
            logger.info("README文件信息: ${readmeInfo.name}, 大小: ${readmeInfo.size}")
            // 解码Base64内容
            val readmeContent = Base64.decodeStr(readmeInfo.content)
            //调用大模型总结
            val summarize = llmSummarize.summarize(readmeContent)
            val projectData = ProjectData(repo, "https://github.com/$owner/$repo", summarize)
            projectDatas.add(projectData)
        }
        page.putField("projectDatas",projectDatas)

    }

    override fun getSite(): Site {
        return site
    }

}