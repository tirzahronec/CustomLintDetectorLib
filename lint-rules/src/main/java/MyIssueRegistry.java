import com.android.tools.lint.detector.api.Issue;
import com.example.lintrules.MyAnnotationDetector;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyIssueRegistry extends com.android.tools.lint.client.api.IssueRegistry {
    public MyIssueRegistry() {

    }


    @NotNull
    @Override
    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<Issue>();
        issues.add(MyAnnotationDetector.CAREFUL_NOW_ISSUE);

        return issues;
    }
}
