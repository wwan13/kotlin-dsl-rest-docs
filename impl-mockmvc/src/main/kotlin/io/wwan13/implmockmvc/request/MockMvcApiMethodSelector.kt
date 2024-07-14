package io.wwan13.implmockmvc.request

import io.wwan13.api.request.ApiMethodSelector
import io.wwan13.api.request.ApiRequestBuilder
import io.wwan13.api.request.DELETE
import io.wwan13.api.request.GET
import io.wwan13.api.request.PATCH
import io.wwan13.api.request.POST
import io.wwan13.api.request.PUT
import org.springframework.test.web.servlet.ResultActions

class MockMvcApiMethodSelector(
    val applyAction: (
        requestBuilder: MockMvcApiRequestBuilder,
        buildAction: ApiRequestBuilder.() -> Unit
    ) -> ResultActions
) : ApiMethodSelector {

    override fun get(
        path: String,
        vararg pathParameters: Any,
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = MockMvcApiRequestBuilder(GET, path, pathParameters)
        return applyAction(requestBuilder, buildAction)
    }

    override fun post(
        path: String,
        vararg pathParameters: Any,
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = MockMvcApiRequestBuilder(POST, path, pathParameters)
        return applyAction(requestBuilder, buildAction)
    }

    override fun put(
        path: String,
        vararg pathParameters: Any,
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = MockMvcApiRequestBuilder(PUT, path, pathParameters)
        return applyAction(requestBuilder, buildAction)
    }

    override fun patch(
        path: String,
        vararg pathParameters: Any,
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = MockMvcApiRequestBuilder(PATCH, path, pathParameters)
        return applyAction(requestBuilder, buildAction)
    }

    override fun delete(
        path: String,
        vararg pathParameters: Any,
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = MockMvcApiRequestBuilder(DELETE, path, pathParameters)
        return applyAction(requestBuilder, buildAction)
    }
}
