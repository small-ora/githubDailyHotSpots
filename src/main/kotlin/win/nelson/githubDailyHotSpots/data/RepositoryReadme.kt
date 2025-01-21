package win.nelson.githubDailyHotSpots.data

data class RepositoryReadme(
    val name: String,
    val path: String,
    val sha: String,
    val size: Int,
    val url: String,
    val htmlUrl: String,
    val gitUrl: String,
    val downloadUrl: String,
    val type: String,
    val content: String,
    val encoding: String,
)
