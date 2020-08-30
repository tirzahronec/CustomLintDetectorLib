import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import java.util.*


val CarefulNow = Issue.create("com.example.lintlib.CarefulNow11",
        "com.example.lintlib.CarefulNow CarefulNowCarefulNowCarefulNowCarefulNowCarefulNow",
        "Dont use! ",
        CORRECTNESS,
        5,
        Severity.WARNING,
        Implementation(MyDetector::class.java, Scope.JAVA_FILE_SCOPE))

class MyIssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(CarefulNow)

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API
}
