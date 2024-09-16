package io.wwan13.api.document

import io.wwan13.api.document.snippets.DocumentCommonEntity
import io.wwan13.api.document.snippets.DocumentEnum
import io.wwan13.api.document.snippets.DocumentError
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentGuide
import io.wwan13.api.document.snippets.DocumentSummary
import io.wwan13.api.document.util.MarkdownConverter
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.headers.ResponseHeadersSnippet
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestParametersSnippet
import org.springframework.restdocs.snippet.Snippet

data class ApiDocumentContext(
    val identifier: String,
    val summary: DocumentSummary,
    val guide: DocumentGuide,
    val enums: List<DocumentEnum>,
    val errors: List<DocumentError>,
    val pathParameters: List<DocumentCommonEntity>,
    val queryParameters: List<DocumentCommonEntity>,
    val requestHeaders: List<DocumentCommonEntity>,
    val requestFields: List<DocumentField>,
    val responseHeaders: List<DocumentCommonEntity>,
    val responseFields: List<DocumentField>,
    val commonResponseFields: List<DocumentField>,
) {

    val snippets: Array<Snippet>
        get() {
            val snippets = mutableListOf<Snippet>()

            if (pathParameters.isNotEmpty()) snippets.add(toPathParameterSnippet())
            if (queryParameters.isNotEmpty()) snippets.add(toRequestParameterSnippet())
            if (requestHeaders.isNotEmpty()) snippets.add(toRequestHeaderSnippet())
            if (requestFields.isNotEmpty()) snippets.add(toRequestFieldSnippet())
            if (responseHeaders.isNotEmpty()) snippets.add(toResponseHeaderSnippet())
            if (allResponseFields.isNotEmpty()) snippets.add(toResponseFieldSnippet())

            return snippets.toTypedArray()
        }

    val description: String
        get() = MarkdownConverter.join(
            summary.description,
            MarkdownConverter.convertGuide(guide),
            MarkdownConverter.convertEnums(enums),
            MarkdownConverter.convertErrors(errors),
        )

    private val allResponseFields: List<DocumentField>
        get() {
            val fields = mutableListOf<DocumentField>()
            fields.addAll(responseFields)
            fields.addAll(commonResponseFields)
            return fields
        }

    private fun toPathParameterSnippet(): PathParametersSnippet {
        return RequestDocumentation.pathParameters(
            pathParameters.map { it.toParameterDescriptor() }
        )
    }

    private fun toRequestParameterSnippet(): RequestParametersSnippet {
        return RequestDocumentation.requestParameters(
            queryParameters.map { it.toParameterDescriptor() }
        )
    }

    private fun toRequestHeaderSnippet(): RequestHeadersSnippet {
        return HeaderDocumentation.requestHeaders(
            requestHeaders.map { it.toHeaderDescriptor() }
        )
    }

    private fun toResponseHeaderSnippet(): ResponseHeadersSnippet {
        return HeaderDocumentation.responseHeaders(
            responseHeaders.map { it.toHeaderDescriptor() }
        )
    }

    private fun toRequestFieldSnippet(): RequestFieldsSnippet {
        return PayloadDocumentation.requestFields(
            requestFields.map { it.toFieldDescriptor() }
        )
    }

    private fun toResponseFieldSnippet(): ResponseFieldsSnippet {
        return PayloadDocumentation.responseFields(
            allResponseFields.map { it.toFieldDescriptor() }
        )
    }
}
