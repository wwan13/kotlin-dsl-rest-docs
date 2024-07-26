package io.wwan13.api.request

import org.springframework.test.web.servlet.ResultActions

interface ApiMethodSelector {

    fun get(
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit = { }
    ): ResultActions

    fun post(
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit = { }
    ): ResultActions

    fun put(
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit = { }
    ): ResultActions

    fun patch(
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit = { }
    ): ResultActions

    fun delete(
        path: String,
        vararg pathParameters: Any = arrayOf(),
        buildAction: ApiRequestBuilder.() -> Unit = { }
    ): ResultActions
}
