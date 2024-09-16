package io.wwan13.api.document.util

import io.wwan13.api.document.snippets.DocumentEnum
import io.wwan13.api.document.snippets.DocumentError
import io.wwan13.api.document.snippets.DocumentGuide

object MarkdownConverter {

    fun convertGuide(guide: DocumentGuide): String {
        return """
            |
            |### Api Guide
            |
            |${guide.value.joinToString("\n") { "- $it" }}
            |
        """.trimMargin()
    }

    fun convertEnums(enums: List<DocumentEnum>): String {
        val enumElements = enums.joinToString("\n") {
            """
            |**${it.name}**
            |
            |${it.values.joinToString("\n", "", "<br/>") { value -> "- $value" }}
            |
            """.trimMargin()
        }

        return """
            |
            |### Enum Values
            |
            |<details>
            |<summary> more </summary>
            |
            |$enumElements
            |
            |</details>
            |
        """.trimMargin()
    }

    fun convertErrors(errors: List<DocumentError>): String {
        return """
            |
            |### Error Codes
            |
            |<details>
            |<summary> more </summary>
            |
            ||ErrorCode|ErrorMessage|
            ||:---:|:---:|
            |${errors.joinToString("\n") { "|${it.code}|${it.message}|" }}
            |
            |</details>
            |
        """.trimMargin()
    }

    fun join(values: List<String>): String {
        return values.joinToString("<br/>")
    }
}
