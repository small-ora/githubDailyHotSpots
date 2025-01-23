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
        msg["content"] = "我会发给你一份github项目的readme,你帮我用中文总结归纳这个项目,要详细描述这个项目是做什么,有什么特点,使用什么语言开发的。总结的描述使用html的格式，方便阅读！"
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