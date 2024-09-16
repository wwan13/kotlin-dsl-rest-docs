package io.wwan13.api.document.snippets

infix fun String.provideMessage(message: String): DocumentError {
    return DocumentError(this, message)
}

data class DocumentError(
    val code: String,
    val message: String
)
