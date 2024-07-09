package io.wwan13.api.document.util

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.operation.preprocess.Preprocessors
import java.util.function.Function

object DocumentUtil {

    fun requestPreprocessor(): OperationRequestPreprocessor {
        return Preprocessors.preprocessRequest()
    }

    fun responsePreprocessor(): OperationResponsePreprocessor {
        return Preprocessors.preprocessResponse()
    }

    fun <T> snippetFilter(): Function<T, T> {
        return Function.identity()
    }
}
