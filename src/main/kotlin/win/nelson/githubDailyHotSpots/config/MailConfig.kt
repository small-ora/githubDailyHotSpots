package win.nelson.githubDailyHotSpots.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "mail")
class MailConfig@ConstructorBinding constructor(
    var from:String,
    var to:String,
    var subject:String,
    var content:String,
    var server:String,
    var port:Int,
    var username:String,
    var password:String
)