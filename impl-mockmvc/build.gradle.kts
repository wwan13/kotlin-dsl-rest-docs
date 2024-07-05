dependencies {
    compileOnly(project(":api"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    implementation("com.epages:restdocs-api-spec-mockmvc:0.16.0")
}
