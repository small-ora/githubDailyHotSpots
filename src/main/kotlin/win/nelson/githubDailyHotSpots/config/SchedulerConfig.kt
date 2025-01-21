package win.nelson.githubDailyHotSpots.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "scheduler")
class SchedulerConfig @ConstructorBinding constructor(
    val cronExpression: String
)