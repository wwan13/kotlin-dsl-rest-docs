package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentCommonEntity
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentSummary
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.headers.ResponseHeadersSnippet
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestParametersSnippet

data class ApiDocumentContext(
    val identifier: String,
    val summary: DocumentSummary,
    val pathParameters: List<DocumentCommonEntity>,
    val queryParameters: List<DocumentCommonEntity>,
    val requestHeaders: List<DocumentCommonEntity>,
    val requestFields: List<DocumentField>,
    val responseHeaders: List<DocumentCommonEntity>,
    val responseFields: List<DocumentField>,
    val commonResponseFields: List<DocumentField>,
) {

    private val allResponseFields: List<DocumentField>
        get() {
            val fields = mutableListOf<DocumentField>()
            fields.addAll(responseFields)
            fields.addAll(commonResponseFields)
            return fields
        }

    fun toPathParameterSnippet(): PathParametersSnippet {
        return RequestDocumentation.pathParameters(
            pathParameters.map { it.toParameterDescriptor() }
        )
    }

    fun toRequestParameterSnippet(): RequestParametersSnippet {
        return RequestDocumentation.requestParameters(
            queryParameters.map { it.toParameterDescriptor() }
        )
    }

    fun toRequestHeaderSnippet(): RequestHeadersSnippet {
        return HeaderDocumentation.requestHeaders(
            requestHeaders.map { it.toHeaderDescriptor() }
        )
    }

    fun toResponseHeaderSnippet(): ResponseHeadersSnippet {
        return HeaderDocumentation.responseHeaders(
            responseHeaders.map { it.toHeaderDescriptor() }
        )
    }

    fun toRequestFieldSnippet(): RequestFieldsSnippet {
        return PayloadDocumentation.requestFields(
            requestFields.map { it.toFieldDescriptor() }
        )
    }

    fun toResponseFieldSnippet(): ResponseFieldsSnippet {
        return PayloadDocumentation.responseFields(
            allResponseFields.map { it.toFieldDescriptor() }
        )
    }
}
