package io.wwan13.implmockmvc

import io.wwan13.api.ApiDocsTest
import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.implmockmvc.docs.MockMvcApiDocumentGenerator
import io.wwan13.implmockmvc.request.MockMvcApiMethodSelector
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureRestDocs
abstract class MockMvcApiDocsTest : ApiDocsTest() {

    init {
        api = MockMvcApiMethodSelector { requestBuilder, buildAction ->
            requestBuilder.apply(buildAction).build(mockMvc())
        }
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
