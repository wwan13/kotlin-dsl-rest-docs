package io.wwan13.implmockmvc.request

import io.wwan13.api.request.ApiRequestBuilder
import io.wwan13.api.request.DELETE
import io.wwan13.api.request.GET
import io.wwan13.api.request.HttpMethod
import io.wwan13.api.request.PATCH
import io.wwan13.api.request.POST
import io.wwan13.api.request.PUT
import io.wwan13.implmockmvc.util.JsonSerializer
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

class MockMvcApiRequestBuilder(
    method: HttpMethod,
    path: String,
    pathParameters: Array<out Any>
) : ApiRequestBuilder {

    private val builder: MockHttpServletRequestBuilder

    init {
        builder = httpMethodOf(method, path, pathParameters)
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
        method: HttpMethod,
        path: String,
        pathParameters: Array<out Any>,
    ): MockHttpServletRequestBuilder {
        return when (method) {
            GET -> RestDocumentationRequestBuilders.get(path, *pathParameters)
            POST -> RestDocumentationRequestBuilders.post(path, *pathParameters)
            PATCH -> RestDocumentationRequestBuilders.patch(path, *pathParameters)
            PUT -> RestDocumentationRequestBuilders.put(path, *pathParameters)
            DELETE -> RestDocumentationRequestBuilders.delete(path, *pathParameters)
        }
    }
}
