package win.nelson.githubDailyHotSpots.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity(name = "project_data")
@Table(
    name = "project_data",
    indexes = [
        Index(name = "idx_project_url", columnList = "projectUrl", unique = true)
    ]
)
data class ProjectData(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(columnDefinition = "varchar(500)")
    val projectName:String,
    @Column(columnDefinition = "varchar(500)")
    val projectUrl:String,
    @Column(columnDefinition = "TEXT")
    val projectDetail:String
)
{
    // 添加无参构造函数
    constructor() : this(null, "", "", "")
}