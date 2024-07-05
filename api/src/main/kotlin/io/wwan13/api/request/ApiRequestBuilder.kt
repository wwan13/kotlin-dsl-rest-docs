package io.wwan13.api.request

interface ApiRequestBuilder {

    fun requestHeader(key: String, value: String): ApiRequestBuilder

    fun bearerToken(token: String): ApiRequestBuilder

    fun queryParam(key: String, value: String): ApiRequestBuilder

    fun requestBody(body: Any): ApiRequestBuilder
}