package com.example.lintrules;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.SourceCodeScanner;
import com.intellij.lang.ASTNode;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiConstructorCall;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;

import org.jetbrains.uast.UCallExpression;

import java.util.Arrays;
import java.util.List;


public class MyAnnotationDetector extends Detector implements Detector.UastScanner {

    public static final Issue CAREFUL_NOW_ISSUE = Issue.create(
            "CarefulNow",
            "Be careful when using this method.",
            "This method has special conditions surrounding it's use," +
                    " be careful when calling it and refer to its documentation.",
            Category.USABILITY,
            7,
            Severity.WARNING,
            new Implementation(
                    MyAnnotationDetector.class,
                    Scope.JAVA_FILE_SCOPE));

    private static final String CAREFUL_NOW_ANNOTATION = "com.example.lintrules.annotations.CarefulNow";

    private static void checkAnnotations(@NonNull JavaContext context,
                                              @NonNull PsiAnnotation []annotations,
                                              @NonNull ASTNode node) {
        for (PsiAnnotation annotation : annotations) {
            String annotationName = annotation.getQualifiedName();
            if (annotationName != null) {
                if (annotationName.equals(CAREFUL_NOW_ANNOTATION) || annotationName.endsWith(".CarefulNow")) {
                    checkCarefulNow(context, node, annotation);
                }
            }
        }
    }

    private static void checkCarefulNow(@NonNull JavaContext context,
                                        @NonNull ASTNode node,
                                        @NonNull PsiAnnotation annotation) {
        context.report(CAREFUL_NOW_ISSUE, node.getPsi(), context.getLocation(node.getPsi()),
                "This method has special conditions surrounding it's use, " +
                        "be careful when calling it and refer to it's documentation.");
    }


    // ---- Implements JavaScanner ----

    @Override
    public List<Class<? extends PsiElement>> getApplicablePsiTypes() {

        return Arrays.asList(
                PsiElement.class,
                PsiMethod.class,
                PsiMethodCallExpression.class,
                PsiClass.class,
                PsiConstructorCall.class); //// ?/???????????????????

    }

//    @Override
//    public JavaElementVisitor createPsiVisitor(@NonNull JavaContext context) {
//        return new CallChecker(context);
//    }

    @Override
    public void visitMethodCall(JavaContext context,
                                UCallExpression node,
                                PsiMethod method) {

        checkAnnotations(context, method.getAnnotations(), method.getNode());
    }

    private static class CallChecker extends JavaRecursiveElementVisitor {

        private final JavaContext mContext;

        public CallChecker(JavaContext context) {
            mContext = context;
        }

        @Override
        public void visitMethodCallExpression(@NonNull PsiMethodCallExpression expression) {
            PsiMethod psiMethod = expression.resolveMethod();
            if (psiMethod != null) {
                checkAnnotations(mContext, psiMethod.getAnnotations(), expression.getNode());
            }
        }

        @Override
        public void visitClass(@NonNull PsiClass psiClass) {
            checkAnnotations(mContext, psiClass.getAnnotations(), psiClass.getNode());
        }

    }

}