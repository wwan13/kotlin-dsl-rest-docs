package io.wwan13.api.request

interface ApiRequestBuilder {

    fun requestHeader(key: String, value: String): ApiRequestBuilder

    fun withBearerToken(): ApiRequestBuilder

    fun queryParam(key: String, value: String): ApiRequestBuilder

    fun requestBody(body: Any): ApiRequestBuilder
}
