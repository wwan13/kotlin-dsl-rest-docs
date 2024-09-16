package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentCommonEntity
import io.wwan13.api.document.snippets.DocumentEnum
import io.wwan13.api.document.snippets.DocumentError
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentGuide
import io.wwan13.api.document.snippets.DocumentSummary

class ApiDocumentContextBuilder(
    private val identifier: String,
    private val tagInitializedInConstructorBlock: String,
    private val commonResponseFields: List<DocumentField>
) {

    private lateinit var summary: DocumentSummary
    private var guide: DocumentGuide = DocumentGuide()
    private val enums: MutableList<DocumentEnum> = mutableListOf()
    private val errors: MutableList<DocumentError> = mutableListOf()
    private val pathParameters: MutableList<DocumentCommonEntity> = mutableListOf()
    private val queryParameters: MutableList<DocumentCommonEntity> = mutableListOf()
    private val requestHeaders: MutableList<DocumentCommonEntity> = mutableListOf()
    private val requestFields: MutableList<DocumentField> = mutableListOf()
    private val responseHeaders: MutableList<DocumentCommonEntity> = mutableListOf()
    private val responseFields: MutableList<DocumentField> = mutableListOf()

    fun summary(summary: DocumentSummary) {
        when (summary.isTagNotInitializedInDslBlock) {
            true -> this.summary = summary.withTag(tagInitializedInConstructorBlock)
            false -> this.summary = summary
        }
    }

    fun summary(summary: String) {
        this.summary = DocumentSummary(summary, tagInitializedInConstructorBlock)
    }

    fun guide(vararg about: String) {
        this.guide = DocumentGuide(about.toList())
    }

    fun containedEnums(vararg values: DocumentEnum) {
        enums.addAll(values)
    }

    fun expectedErrors(vararg errors: DocumentError) {
        this.errors.addAll(errors)
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
            identifier, summary, guide, enums, errors,
            pathParameters, queryParameters,
            requestHeaders, requestFields,
            responseHeaders, responseFields, commonResponseFields
        )
    }
}
