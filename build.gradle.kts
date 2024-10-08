plugins {
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("maven-publish")
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "maven-publish")

    group = "io.wwan13"
    version = "1.2.0"

    repositories {
        mavenCentral()
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}

subprojects {
    kotlin {
        jvmToolchain(17)
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks {
        test {
            useJUnitPlatform()
        }

        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "17"
            }
        }

        bootJar {
            enabled = false
            archiveClassifier.set("")
        }

        jar {
            enabled = true
            archiveClassifier.set("")
        }
    }
}

tasks {
    wrapper {
        gradleVersion = "7.5"
        distributionType = Wrapper.DistributionType.ALL
    }

    bootJar {
        enabled = false
        archiveClassifier.set("")
    }

    jar {
        enabled = true
        archiveClassifier.set("")
    }
}
