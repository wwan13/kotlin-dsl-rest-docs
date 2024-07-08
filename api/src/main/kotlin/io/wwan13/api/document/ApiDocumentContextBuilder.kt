package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentCommonEntity
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentAbout

class ApiDocumentContextBuilder(
    private val identifier: String,
    private val commonResponseFields: List<DocumentField>
) {

    private lateinit var about: DocumentAbout
    private val pathParameters: MutableList<DocumentCommonEntity> = mutableListOf()
    private val queryParameters: MutableList<DocumentCommonEntity> = mutableListOf()
    private val requestHeaders: MutableList<DocumentCommonEntity> = mutableListOf()
    private val requestFields: MutableList<DocumentField> = mutableListOf()
    private val responseHeaders: MutableList<DocumentCommonEntity> = mutableListOf()
    private val responseFields: MutableList<DocumentField> = mutableListOf()

    fun about(resource: DocumentAbout) {
        this.about = resource
    }

    fun pathParameters(vararg parameters: DocumentCommonEntity) {
        pathParameters.addAll(parameters)
    }

    fun queryParameters(vararg parameters: DocumentCommonEntity) {
        queryParameters.addAll(parameters)
    }

    fun requestHeaders(vararg headers: DocumentCommonEntity) {
        requestHeaders.addAll(headers)
    }

    fun requestFields(vararg fields: DocumentField) {
        requestFields.addAll(fields)
    }

    fun responseHeaders(vararg headers: DocumentCommonEntity) {
        responseHeaders.addAll(headers)
    }

    fun responseFields(vararg fields: DocumentField) {
        responseFields.addAll(fields)
    }

    fun build(): ApiDocumentContext {
        return ApiDocumentContext(
            identifier, about,
            pathParameters, queryParameters,
            requestHeaders, requestFields,
            responseHeaders, responseFields, commonResponseFields
        )
    }
}
