dependencies {
    compileOnly(project(":api"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("com.epages:restdocs-api-spec-mockmvc:0.17.1")
    implementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.0")
}
