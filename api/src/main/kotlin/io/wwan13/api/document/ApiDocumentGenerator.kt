package io.wwan13.api.document

import org.springframework.test.web.servlet.ResultHandler

interface ApiDocumentGenerator {

    fun generate(context: ApiDocumentContext): ResultHandler
}