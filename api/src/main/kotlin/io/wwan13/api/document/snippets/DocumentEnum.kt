package io.wwan13.api.document.snippets

infix fun String.hasValues(values: List<String>): DocumentEnum {
    return DocumentEnum(this, values)
}

data class DocumentEnum(
    val name: String,
    val values: List<String> = listOf()
)
