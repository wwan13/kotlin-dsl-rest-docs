package io.wwan13.implmockmvc.util

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JsonSerializer {

    private val objectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    fun serialize(value: Any): String {
        return objectMapper.writeValueAsString(value)
    }
}
