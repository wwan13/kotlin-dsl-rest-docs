package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentAbout
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentParameter

data class ApiDocumentContext(
    val identifier: String,
    val about: DocumentAbout,
    val pathParameters: List<DocumentParameter>,
    val queryParameters: List<DocumentParameter>,
    val requestFields: List<DocumentField>,
    val responseFields: List<DocumentField>,
    val commonResponseFields: List<DocumentField>,
) {

    val allResponseFields: List<DocumentField>
        get() {
            val fields = mutableListOf<DocumentField>()
            fields.addAll(responseFields)
            fields.addAll(commonResponseFields)
            return fields
        }
}
