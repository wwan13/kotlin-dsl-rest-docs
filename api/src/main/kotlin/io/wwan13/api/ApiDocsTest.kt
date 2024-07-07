package io.wwan13.api

import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.request.ApiRequestBuilder
import io.wwan13.api.request.HttpMethod
import org.springframework.test.web.servlet.ResultActions

interface ApiDocsTest {

    fun api(
        method: HttpMethod,
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions

    fun documentFor(
        resultAction: ResultActions,
        identifier: String,
        action: ApiDocumentContextBuilder.() -> Unit
    )

    fun commonResponseField(): List<DocumentField> {
        return listOf()
    }
}
