package io.wwan13.api.document.snippets

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation

infix fun String.whichMeans(description: String): DocumentCommonEntity {
    return DocumentCommonEntity(this, description)
}

infix fun DocumentCommonEntity.required(required: Boolean): DocumentCommonEntity {
    return DocumentCommonEntity(identifier, description, required)
}

data class DocumentCommonEntity(
    val identifier: String,
    val description: String = "",
    val required: Boolean = true
) {

    fun toParameterDescriptor(): ParameterDescriptor {
        val descriptor = RequestDocumentation
            .parameterWithName(identifier)
            .description(description)
        if (!required) {
            descriptor.optional()
        }
        return descriptor
    }

    fun toHeaderDescriptor(): HeaderDescriptor {
        val descriptor = HeaderDocumentation
            .headerWithName(identifier)
            .description(description)
        if (!required) {
            descriptor.optional()
        }
        return descriptor
    }
}
