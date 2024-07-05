package io.wwan13.implmockmvc.docs

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import com.epages.restdocs.apispec.ResourceSnippetDetails
import io.wwan13.api.document.snippets.DocumentAbout
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentParameter
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

fun DocumentParameter.toParameterDescriptor(): ParameterDescriptor {
    val descriptor = RequestDocumentation
        .parameterWithName(identifier)
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