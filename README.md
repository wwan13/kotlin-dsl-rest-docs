# Kotlin Dsl Rest Docs
[![](https://jitpack.io/v/wwan13/kotlin-dsl-rest-docs.svg)](https://jitpack.io/#wwan13/kotlin-dsl-rest-docs)

## AS IS
~~~kotlin
@Test
fun `회원가입 API`() {

    val request = SignInRequest(
        username = "username",
        password = "raw password",
        passwordConfirm = "raw password",
        nickname = "nickname"
    )

    every { userService.appendNormalUser(any(), any()) } returns 1L
    every { passwordEncoder.encode(any()) } returns "encoded password"

    mockMvc
        .perform(
            post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(objectMapper.writeValueAsString(request))
        )
        .andExpect(status().isOk)
        .andDo(MockMvcResultHandlers.print())
        .andDo(
            document(
                "sign-in",
                resourceDetails().description("asd").tags("api"),
                RestDocsUtils.requestPreprocessor(),
                RestDocsUtils.responsePreprocessor(),
                Function.identity(),
                requestParameters(),
                pathParameters(),
                requestFields(
                    fieldWithPath("username")
                        .type(JsonFieldType.STRING)
                        .description("유저 아이디"),

                    fieldWithPath("password")
                        .type(JsonFieldType.STRING)
                        .description("유저 비밀번호"),

                    fieldWithPath("passwordConfirm")
                        .type(JsonFieldType.STRING)
                        .description("비밀번호 확인"),

                    fieldWithPath("nickname")
                        .type(JsonFieldType.STRING)
                        .description("닉네임"),
                ),
                responseFields(
                    fieldWithPath("status")
                        .type(JsonFieldType.STRING)
                        .description("응답 상태"),
                    fieldWithPath("timestamp")
                        .type(JsonFieldType.STRING)
                        .description("응답 시간"),
                    fieldWithPath("data.id")
                        .type(JsonFieldType.NUMBER)
                        .description("생성된 user id"),
                )
            )
        )
}
~~~

## TO BE
~~~kotlin
@Test
fun `회원가입 API`() {

    every { userService.appendNormalUser(any(), any()) } returns 1L
    every { passwordEncoder.encode(any()) } returns "encoded password"

    val api = api.post("/api/v1/user") {
        requestBody(
            SignInRequest(
                username = "username",
                password = "raw password",
                passwordConfirm = "raw password",
                nickname = "nickname"
            )
        )
    }

    documentFor(api, "sign-in") {
        about("회원가입 API" withTag "user")
        requestFields(
            "username" isTypeOf STRING whichMeans "유저 아이디",
            "password" isTypeOf STRING whichMeans "유저 비밀번호",
            "passwordConfirm" isTypeOf STRING whichMeans "비밀번호 확인",
            "nickname" isTypeOf STRING whichMeans "유저 닉네임"
        )
        responseFields(
            "data.id" isTypeOf NUMBER whichMeans "생성된 사용자 ID"
        )
    }
}
~~~
