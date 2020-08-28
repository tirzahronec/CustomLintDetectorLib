package com.example.annotationinsp;

import com.android.tools.lint.detector.api.Issue;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IssueRegistry extends com.android.tools.lint.client.api.IssueRegistry {
    public IssueRegistry() {

    }

    @NotNull
    @Override
    public List<Issue> getIssues() {
        return new ArrayList<Issue>() {{
            add(MyAnnotationDetector.CAREFUL_NOW_ISSUE);
        }};
    }
}
