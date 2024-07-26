dependencies {
    api(project(":api"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("com.epages:restdocs-api-spec-mockmvc:0.16.0")
}
