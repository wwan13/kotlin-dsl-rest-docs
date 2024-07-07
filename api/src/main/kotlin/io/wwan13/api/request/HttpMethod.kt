package io.wwan13.api.request

sealed class HttpMethod(
    val method: String
)

data object GET : HttpMethod("GET")
data object POST : HttpMethod("POST")
data object PUT : HttpMethod("PUT")
data object PATCH : HttpMethod("PATCH")
data object DELETE : HttpMethod("DELETE")
