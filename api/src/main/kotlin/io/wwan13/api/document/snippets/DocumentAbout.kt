package io.wwan13.api.document.snippets

infix fun String.withTag(tag: String): DocumentAbout {
    return DocumentAbout(this, tag)
}

infix fun DocumentAbout.moreAbout(description: String): DocumentAbout {
    return DocumentAbout(this.summary, this.tag, description)
}

data class DocumentAbout(
    val summary: String,
    var tag: String,
    val description: String = ""
)
