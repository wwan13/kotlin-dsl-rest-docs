package io.wwan13.implmockmvc.request

import io.wwan13.api.request.ApiRequestBuilder
import io.wwan13.implmockmvc.util.JsonSerializer
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

class MockMvcApiRequestBuilder(
    method: String,
    path: String,
    pathParameters: List<Any>
) : ApiRequestBuilder {

    private val builder: MockHttpServletRequestBuilder

    init {
        builder = httpMethodOf(method, path, pathParameters.toList())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8")
    }

    override fun requestHeader(
        key: String,
        value: String
    ): ApiRequestBuilder {
        builder.header(key, value)
        return this
    }

    override fun withBearerToken(): ApiRequestBuilder {
        builder.header("Authorization", "Bearer {bearer token}")
        return this
    }

    override fun queryParam(
        key: String,
        value: String
    ): ApiRequestBuilder {
        builder.queryParam(key, value)
        return this
    }

    override fun requestBody(
        body: Any
    ): ApiRequestBuilder {
        builder.content(JsonSerializer.serialize(body))
        return this
    }

    fun build(mockMvc: MockMvc): ResultActions {
        return mockMvc.perform(builder)
    }

    private fun httpMethodOf(
        method: String,
        path: String,
        pathParameters: List<Any>,
    ): MockHttpServletRequestBuilder {
        return when (method) {
            "GET" -> RestDocumentationRequestBuilders.get(path, pathParameters)
            "POST" -> RestDocumentationRequestBuilders.post(path, pathParameters)
            "PATCH" -> RestDocumentationRequestBuilders.patch(path, pathParameters)
            "PUT" -> RestDocumentationRequestBuilders.put(path, pathParameters)
            "DELETE" -> RestDocumentationRequestBuilders.delete(path, pathParameters)
            else -> throw IllegalArgumentException("invalid http method option")
        }
    }
}