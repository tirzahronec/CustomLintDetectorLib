import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

class MyDetector : Detector(), Detector.UastScanner {

    override fun getApplicableUastTypes() = listOf(UCallExpression::class.java)

    override fun createUastHandler(context: JavaContext) = MethodCallHandler(context)

}

class MethodCallHandler(private val context: JavaContext) : UElementHandler() {
    override fun visitCallExpression(node: UCallExpression) {
        var method: PsiMethod? = node.resolve() ?: return
        method?.annotations?.forEach { annotation ->
            if (annotation.qualifiedName.equals("CarefulNow11")) {
                context.report(CarefulNow, node, context.getLocation(method), "YOU JUST CANT")
            }
        }

    }
}

