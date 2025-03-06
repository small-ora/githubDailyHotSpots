package win.nelson.githubDailyHotSpots.data

data class RepositoryReadme(
    var name: String? = null,
    var path: String? = null,
    var sha: String? = null,
    var size: Int? = null,
    var url: String? = null,
    var htmlUrl: String? = null,
    var gitUrl: String? = null,
    var downloadUrl: String? = null,
    var type: String? = null,
    var content: String? = null,
    var encoding: String? = null,
)
