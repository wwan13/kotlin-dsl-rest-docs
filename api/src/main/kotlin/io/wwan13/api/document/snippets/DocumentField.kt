package io.wwan13.api.document.snippets

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation

infix fun String.isTypeOf(type: FieldType): DocumentField {
    return DocumentField(this, type)
}

infix fun <T : Enum<T>> String.isTypeOf(enumType: ENUM<T>): DocumentField {
    return DocumentField(
        identifier = this,
        type = enumType,
        enumValues = " (${enumType.entries.joinToString(", ")})"
    )
}

infix fun DocumentField.whichMeans(description: String): DocumentField {
    return DocumentField(this.identifier, this.type, description)
}

infix fun DocumentField.required(required: Boolean): DocumentField {
    return DocumentField(this.identifier, this.type, description, required)
}

data class DocumentField(
    val identifier: String,
    val type: FieldType,
    var description: String = "",
    var required: Boolean = true,
    val enumValues: String? = null
) {
    fun toFieldDescriptor(): FieldDescriptor {
        val descriptor = PayloadDocumentation.fieldWithPath(identifier)
            .type(type.value)
            .description(description + enumValues)
        if (!required) {
            descriptor.optional()
        }
        return descriptor
    }
}
