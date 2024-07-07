package io.wwan13.api

import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.request.ApiMethodSelector
import org.springframework.test.web.servlet.ResultActions

abstract class ApiDocsTest {

    protected lateinit var api: ApiMethodSelector

    abstract fun documentFor(
        resultAction: ResultActions,
        identifier: String,
        action: ApiDocumentContextBuilder.() -> Unit
    )

    fun commonResponseField(): List<DocumentField> {
        return listOf()
    }
}
