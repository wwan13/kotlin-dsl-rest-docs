package io.wwan13.api.document.snippets

data class DocumentGuide(
    val value: List<String> = listOf()
) {

    fun hasValue(): Boolean {
        return value.isNotEmpty()
    }
}
