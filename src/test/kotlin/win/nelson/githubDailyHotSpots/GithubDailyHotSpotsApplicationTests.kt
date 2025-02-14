package win.nelson.githubDailyHotSpots

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import us.codecraft.webmagic.ResultItems
import win.nelson.githubDailyHotSpots.data.ProjectData
import win.nelson.githubDailyHotSpots.pipeline.MailPipeline


@SpringBootTest
class GithubDailyHotSpotsApplicationTests() {

    @Autowired // 使用Spring自动注入
    private val mailPipeline: MailPipeline? = null

    @Test
    fun contextLoads() {
        val resultItems = ResultItems();
        val list : MutableList<ProjectData> = mutableListOf(
            ProjectData("project1", "https://github.com/project1", "Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3\n" +
                    "WARNING: A Java agent has been loaded dynamically (D:\\develop\\gradle_repo\\caches\\modules-2\\files-2.1\\net.bytebuddy\\byte-buddy-agent\\1.15.11\\a38b16385e867f59a641330f0362ebe742788ed8\\byte-buddy-agent-1.15.11.jar)\n" +
                    "WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning\n" +
                    "WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information\n" +
                    "WARNING: Dynamic loading of agents will be disallowed by default in a future release\n" +
                    "Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended"),
            ProjectData("project2", "https://github.com/project2", "Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3\\n\" +\n" +
                    "                    \"WARNING: A Java agent has been loaded dynamically (D:\\\\develop\\\\gradle_repo\\\\caches\\\\modules-2\\\\files-2.1\\\\net.bytebuddy\\\\byte-buddy-agent\\\\1.15.11\\\\a38b16385e867f59a641330f0362ebe742788ed8\\\\byte-buddy-agent-1.15.11.jar)\\n\" +\n" +
                    "                    \"WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning\\n\" +\n" +
                    "                    \"WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information\\n\" +\n" +
                    "                    \"WARNING: Dynamic loading of agents will be disallowed by default in a future release\\n\" +\n" +
                    "                    \"Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended"),
            ProjectData("project3", "https://github.com/project3", "Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3\\n\" +\n" +
                    "                    \"WARNING: A Java agent has been loaded dynamically (D:\\\\develop\\\\gradle_repo\\\\caches\\\\modules-2\\\\files-2.1\\\\net.bytebuddy\\\\byte-buddy-agent\\\\1.15.11\\\\a38b16385e867f59a641330f0362ebe742788ed8\\\\byte-buddy-agent-1.15.11.jar)\\n\" +\n" +
                    "                    \"WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning\\n\" +\n" +
                    "                    \"WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information\\n\" +\n" +
                    "                    \"WARNING: Dynamic loading of agents will be disallowed by default in a future release\\n\" +\n" +
                    "                    \"Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended"),
            ProjectData("project4", "https://github.com/project4", "Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build what is described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3\\n\" +\n" +
                    "                    \"WARNING: A Java agent has been loaded dynamically (D:\\\\develop\\\\gradle_repo\\\\caches\\\\modules-2\\\\files-2.1\\\\net.bytebuddy\\\\byte-buddy-agent\\\\1.15.11\\\\a38b16385e867f59a641330f0362ebe742788ed8\\\\byte-buddy-agent-1.15.11.jar)\\n\" +\n" +
                    "                    \"WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning\\n\" +\n" +
                    "                    \"WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information\\n\" +\n" +
                    "                    \"WARNING: Dynamic loading of agents will be disallowed by default in a future release\\n\" +\n" +
                    "                    \"Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended")
        )
        resultItems.put<MutableList<ProjectData>>("projects",list)
        mailPipeline?.process(resultItems, null)
    }




}
