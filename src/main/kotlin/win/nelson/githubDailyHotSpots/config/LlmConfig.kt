package win.nelson.githubDailyHotSpots.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties(prefix = "llm")
class LlmConfig @ConstructorBinding constructor(
    var url:String,
    var key:String,
    var model:String)