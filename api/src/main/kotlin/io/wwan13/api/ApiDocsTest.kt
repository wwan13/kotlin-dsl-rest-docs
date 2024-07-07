package io.wwan13.api

import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.document.snippets.DocumentField
import org.springframework.test.web.servlet.ResultActions

interface ApiDocsTest {

    fun documentFor(
        resultAction: ResultActions,
        identifier: String,
        action: ApiDocumentContextBuilder.() -> Unit
    )

    fun commonResponseField(): List<DocumentField> {
        return listOf()
    }
}
