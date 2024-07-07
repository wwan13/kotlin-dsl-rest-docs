package io.wwan13.implmockmvc

import io.wwan13.api.ApiDocsTest
import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.request.ApiRequestBuilder
import io.wwan13.api.request.HttpMethod
import io.wwan13.implmockmvc.docs.MockMvcApiDocumentGenerator
import io.wwan13.implmockmvc.request.MockMvcApiRequestBuilder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

abstract class MockMvcApiDocsTest : ApiDocsTest {

    override fun api(
        method: HttpMethod,
        path: String,
        vararg pathParameters: Any,
        buildAction: ApiRequestBuilder.() -> Unit
    ): ResultActions {
        val requestBuilder = MockMvcApiRequestBuilder(method, path, pathParameters.toList())
        return requestBuilder.apply(buildAction).build(mockMvc())
    }

    override fun documentFor(
        resultAction: ResultActions,
        identifier: String,
        action: ApiDocumentContextBuilder.() -> Unit
    ) {
        val builder = ApiDocumentContextBuilder(identifier, commonResponseField())
        val context = builder.apply(action).build()
        val documentGenerator = MockMvcApiDocumentGenerator()
        resultAction.andDo(documentGenerator.generate(context))
    }

    abstract fun mockMvc(): MockMvc
}
