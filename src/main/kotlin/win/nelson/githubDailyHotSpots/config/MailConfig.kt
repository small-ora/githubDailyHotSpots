package win.nelson.githubDailyHotSpots.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "mail")
class MailConfig@ConstructorBinding constructor(
    var to:String,
    var subject:String,
    var from :String,
)