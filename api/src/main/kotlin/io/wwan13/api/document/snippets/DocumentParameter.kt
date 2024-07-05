package io.wwan13.api.document.snippets

infix fun String.whichMeans(description: String): DocumentParameter {
    return DocumentParameter(this, description)
}

infix fun DocumentParameter.required(required: Boolean): DocumentParameter {
    return DocumentParameter(this.identifier, this.description, required)
}

data class DocumentParameter(
    val identifier: String,
    val description: String = "",
    val required: Boolean = true
)
