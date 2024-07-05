package io.wwan13.api

import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.request.ApiRequestBuilder
import org.springframework.test.web.servlet.ResultActions

interface ApiDocsTest {

    fun api(
        method: String,
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions

    fun documentFor(
        resultAction: ResultActions,
        identifier: String,
        action: ApiDocumentContextBuilder.() -> Unit
    )
}