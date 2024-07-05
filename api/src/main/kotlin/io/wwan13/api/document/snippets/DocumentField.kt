package io.wwan13.api.document.snippets

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
)