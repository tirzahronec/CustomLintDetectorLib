package com.example.annotationinsp;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.checks.infrastructure.TestLintResult;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import junit.framework.TestResult;

import java.util.Collections;
import java.util.List;

;

public class MyAnnotationDetectorTest extends LintDetectorTest {
    public void testBasic() {
        TestLintResult testResult = lint().files(
                java("package com.example.myapplication;\n" +
                        "\n" +
                        "\n" +
                        "import com.example.annotationinsp.MyLibClass;\n" +
                        "import com.example.annotationinsp.annotations.CarefulNow;\n" +
                        "\n" +
                        "public class M {\n" +
                        "\n" +
                        "    @CarefulNow\n" +
                        "    public void v() {\n" +
                        "        MyLibClass myLibClass = new MyLibClass();\n" +
                        "        myLibClass.someDangerousMethod();\n" +
                        "    }\n" +
                        "\n" +
                        "    public void u() {\n" +
                        "        MyLibClass myLibClass = new MyLibClass();\n" +
                        "        v();\n" +
                        "    }\n" +
                        "}\n"))
                .detector(new MyAnnotationDetector()).run();
    }

    @Override
    public Detector getDetector() {
        return new MyAnnotationDetector();
    }

    @Override
    public List<Issue> getIssues() {
        return Collections.singletonList(MyAnnotationDetector.CAREFUL_NOW_ISSUE);
    }
}
