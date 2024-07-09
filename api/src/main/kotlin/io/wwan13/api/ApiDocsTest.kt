package io.wwan13.api

import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentSummary
import org.springframework.test.web.servlet.ResultActions

interface ApiDocsTest {

    fun documentFor(
        resultAction: ResultActions,
        identifier: String,
        action: ApiDocumentContextBuilder.() -> Unit
    )

    fun tag(): String {
        return DocumentSummary.DEFAULT_DOCUMENT_TAG
    }

    fun commonResponseField(): List<DocumentField> {
        return listOf()
    }
}
