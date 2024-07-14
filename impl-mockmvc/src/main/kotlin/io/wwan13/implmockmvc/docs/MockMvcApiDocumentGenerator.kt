package io.wwan13.implmockmvc.docs

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import com.epages.restdocs.apispec.ResourceSnippetDetails
import io.wwan13.api.document.ApiDocumentContext
import io.wwan13.api.document.ApiDocumentGenerator
import io.wwan13.api.document.util.DocumentUtil
import org.springframework.test.web.servlet.ResultHandler

fun ApiDocumentContext.toResourceDetail(): ResourceSnippetDetails {
    return MockMvcRestDocumentationWrapper.resourceDetails()
        .summary(summary.summary)
        .description(summary.description)
        .tag(summary.tag)
}

class MockMvcApiDocumentGenerator : ApiDocumentGenerator {

    override fun generate(context: ApiDocumentContext): ResultHandler {
        return MockMvcRestDocumentationWrapper.document(
            context.identifier,

            context.toResourceDetail(),

            DocumentUtil.requestPreprocessor(),
            DocumentUtil.responsePreprocessor(),

            DocumentUtil.snippetFilter(),

            *context.snippets
        )
    }
}
