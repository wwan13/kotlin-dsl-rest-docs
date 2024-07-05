package io.wwan13.implmockmvc.docs

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper
import io.wwan13.api.document.ApiDocumentContext
import io.wwan13.api.document.ApiDocumentGenerator
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.test.web.servlet.ResultHandler
import java.util.function.Function

class MockMvcApiDocumentGenerator : ApiDocumentGenerator {

    override fun generate(context: ApiDocumentContext): ResultHandler {
        return MockMvcRestDocumentationWrapper.document(
            context.identifier,

            context.about.toResourceDetail(),

            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),

            Function.identity(),

            RequestDocumentation.requestParameters(
                context.queryParameters.map { it.toParameterDescriptor() }
            ),
            RequestDocumentation.pathParameters(
                context.pathParameters.map { it.toParameterDescriptor() }
            ),

            PayloadDocumentation.requestFields(
                context.requestFields.map { it.toFieldDescriptor() }
            ),
            PayloadDocumentation.responseFields(
                context.responseFields
                    .union(context.commonResponseFields).map { it.toFieldDescriptor() }
            )
        )
    }
}
