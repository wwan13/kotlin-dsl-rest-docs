package io.wwan13.api.document.snippets

infix fun String.withTag(tag: String): DocumentSummary {
    return DocumentSummary(this, tag)
}

infix fun String.moreAbout(description: String): DocumentSummary {
    return DocumentSummary(this, description = description)
}

infix fun DocumentSummary.moreAbout(description: String): DocumentSummary {
    return DocumentSummary(summary, tag, description)
}

data class DocumentSummary(
    val summary: String,
    val tag: String = DEFAULT_DOCUMENT_TAG,
    val description: String = ""
) {

    val isTagNotInitializedInDslBlock: Boolean
        get() = tag == DEFAULT_DOCUMENT_TAG

    fun withTag(
        customTag: String
    ): DocumentSummary {
        return DocumentSummary(summary, customTag, description)
    }

    companion object {
        const val DEFAULT_DOCUMENT_TAG = "api"
    }
}
