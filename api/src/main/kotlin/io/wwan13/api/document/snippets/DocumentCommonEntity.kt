package io.wwan13.api.document.snippets

infix fun String.whichMeans(description: String): DocumentCommonEntity {
    return DocumentCommonEntity(this, description)
}

infix fun DocumentCommonEntity.required(required: Boolean): DocumentCommonEntity {
    return DocumentCommonEntity(this.identifier, this.description, required)
}

data class DocumentCommonEntity(
    val identifier: String,
    val description: String = "",
    val required: Boolean = true
)
