package win.nelson.githubDailyHotSpots.repository

import org.springframework.data.jpa.repository.JpaRepository
import win.nelson.githubDailyHotSpots.data.ProjectData

interface ProjectRepository : JpaRepository<ProjectData, Long> {


    fun findByProjectUrlIgnoreCase(projectUrl: String): ProjectData?
}