package io.wwan13.implmockmvc.docs

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import com.epages.restdocs.apispec.ResourceSnippetDetails
import io.wwan13.api.document.snippets.DocumentCommonEntity
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentAbout
import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation

fun DocumentField.toFieldDescriptor(): FieldDescriptor {
    val descriptor = PayloadDocumentation.fieldWithPath(identifier)
        .type(type.value)
        .description(description + enumValues)

    if (!required) {
        descriptor.optional()
    }

    return descriptor
}

fun DocumentCommonEntity.toParameterDescriptor(): ParameterDescriptor {
    val descriptor = RequestDocumentation
        .parameterWithName(identifier)
        .description(description)

    if (!required) {
        descriptor.optional()
    }

    return descriptor
}

fun DocumentCommonEntity.toHeaderDescriptor(): HeaderDescriptor {
    val descriptor = HeaderDocumentation
        .headerWithName(identifier)
        .description(description)

    if (!required) {
        descriptor.optional()
    }

    return descriptor
}

fun DocumentAbout.toResourceDetail(): ResourceSnippetDetails {
    return MockMvcRestDocumentationWrapper.resourceDetails()
        .summary(summary)
        .description(description)
        .tag(tag)
}
