package io.wwan13.api.document.snippets

import org.springframework.restdocs.payload.JsonFieldType
import kotlin.reflect.KClass

sealed class FieldType(
    val value: JsonFieldType
)

data object ARRAY : FieldType(JsonFieldType.ARRAY)
data object BOOLEAN : FieldType(JsonFieldType.ARRAY)
data object OBJECT : FieldType(JsonFieldType.OBJECT)
data object NUMBER : FieldType(JsonFieldType.NUMBER)
data object NULL : FieldType(JsonFieldType.NULL)
data object STRING : FieldType(JsonFieldType.STRING)
data object ANY : FieldType(JsonFieldType.VARIES)
data object DATE : FieldType(JsonFieldType.STRING)
data object DATETIME : FieldType(JsonFieldType.STRING)

data class ENUM<T : Enum<T>>(
    val enumClass: KClass<T>
) : FieldType(JsonFieldType.STRING) {

    val entries: List<T>
        get() = enumClass.java.enumConstants.toList()
}
