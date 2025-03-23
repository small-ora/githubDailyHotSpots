package win.nelson.githubDailyHotSpots.llm

import cn.hutool.http.HttpRequest
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import org.springframework.stereotype.Component
import win.nelson.githubDailyHotSpots.config.LlmConfig

@Component
class LlmSummarize(val llmConfig: LlmConfig) {

    fun summarize(content:String):String{
        val bodyJson = JSONObject()
        bodyJson["model"] = llmConfig.model
        bodyJson["stream"] = false
        val message = JSONArray()
        var msg: MutableMap<String, String> = HashMap()
        msg["role"] = "system"
        msg["content"] = """
            作为我的技术洞察助手，请用简洁的中文总结以下Github项目的README文件内容。要求：
            ✦ 核心价值（1句话，必须含量化指标）
            ✦ 创新亮点（不少于3项，带#技术标签）
            ✦ 应用场景（至少2个典型用例） 
            ✦ 资源速览（文档+许可证）
            【输出规则】
            1. 技术标签用中文分类（如#分布式架构）
            2. 量化指标用「」标出（例：节省「70%内存」）
            3. 避免出现版本号/安装步骤
            4. 总字数<250字
            5.语言风格：专业但不失易读性，适合非技术人员理解
            请将项目总结转换为带简单 HTML 标签的格式，要求：
            1. 用 <h4> 表示二级标题，用 <p> 包裹段落
            2. 核心特性用 <ul><li> 列表呈现
            3. 技术关键词用 <strong> 加粗
            4. 每段开头用 emoji 符号引导
            5. 避免复杂 CSS 样式

        """.trimIndent()
        message.add(msg)
        msg = HashMap()
        msg["role"] = "user"
        msg["content"] = content
        message.add(msg)
        bodyJson["messages"] = message

        val body = HttpRequest.post(llmConfig.url)
            .auth("Bearer " + llmConfig.key)
            .body(bodyJson.toJSONString()).execute().body()
        val parse = JSONObject.parse(body)
        val choices = parse.getJSONArray("choices")[0] as JSONObject
        return choices.getJSONObject("message").getString("content")

    }
}