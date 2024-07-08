package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentCommonEntity
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentAbout

data class ApiDocumentContext(
    val identifier: String,
    val about: DocumentAbout,
    val pathParameters: List<DocumentCommonEntity>,
    val queryParameters: List<DocumentCommonEntity>,
    val requestHeaders: List<DocumentCommonEntity>,
    val requestFields: List<DocumentField>,
    val responseHeaders: List<DocumentCommonEntity>,
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
