package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentAbout
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentParameter

class ApiDocumentContextBuilder(
    private val identifier: String,
    private val commonResponseFields: List<DocumentField>
) {

    private lateinit var about: DocumentAbout
    private val pathParameters: MutableList<DocumentParameter> = mutableListOf()
    private val queryParameters: MutableList<DocumentParameter> = mutableListOf()
    private val requestFields: MutableList<DocumentField> = mutableListOf()
    private val responseFields: MutableList<DocumentField> = mutableListOf()

    fun about(resource: DocumentAbout) {
        this.about = resource
    }

    fun pathParameters(vararg parameters: DocumentParameter) {
        pathParameters.addAll(parameters)
    }

    fun queryParameters(vararg parameters: DocumentParameter) {
        queryParameters.addAll(parameters)
    }

    fun requestFields(vararg fields: DocumentField) {
        requestFields.addAll(fields)
    }

    fun responseFields(vararg fields: DocumentField) {
        responseFields.addAll(fields)
    }

    fun build(): ApiDocumentContext {
        return ApiDocumentContext(
            identifier, about, pathParameters, queryParameters,
            requestFields, responseFields, commonResponseFields
        )
    }
}