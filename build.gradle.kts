import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

/*
 * This file was generated by the Gradle 'init' task.
 */

val grpcVersion = "3.19.3"
val grpcKotlinVersion = "1.2.1"
val grpcProtoVersion = "1.45.1"
val armeriaVersion = "1.16.0"

group = "com.er"
version = "0.0.1-SNAPSHOT"
description = "kotlintoy"

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    id("com.google.protobuf") version "0.8.18"


}

apply(plugin = "java")
apply(plugin = "idea")
apply(plugin = "eclipse")

java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.netty:netty-bom:4.1.76.Final"))

    // SPRING BOOT 설정
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.projectlombok:lombok:1.18.24")

    // mapstruct 적용
    implementation("org.mapstruct:mapstruct:1.5.0.RC1")
    kapt("org.mapstruct:mapstruct-processor:1.5.0.RC1")

    // GRPC 설정
    implementation("io.grpc:grpc-stub:$grpcProtoVersion")
    implementation("io.grpc:grpc-protobuf:$grpcProtoVersion")
    implementation("io.grpc:grpc-kotlin-stub:1.2.0")
    implementation("io.grpc:grpc-netty-shaded:$grpcProtoVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$grpcVersion")

    runtimeOnly("org.springframework.boot:spring-boot-devtools:2.6.7")
    runtimeOnly("org.slf4j:log4j-over-slf4j:1.7.36")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.7")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo:3.0.0")
    testImplementation("io.projectreactor:reactor-test:3.4.17")
    testImplementation("org.springframework.restdocs:spring-restdocs-webtestclient:2.0.6.RELEASE")

    implementation("net.devh:grpc-spring-boot-starter:2.13.1.RELEASE")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.4.2.Final")
}


// gradle build시 srcDirs에 파일 생성
// gradle build의 target으로 잡히기 위해서 scrDirs 안에 path를 설정한다.
// (Client가 호출하는) sutb 클래스가 생성될 때의 경로를 설정한다.
sourceSets {
    getByName("main") {
        java {
            srcDirs(
                "build/generated/source/proto/main/java",
                "build/generated/source/proto/main/kotlin"
            )
        }
    }
}


// protobuf 생성 및 stub 클래스(java, kotlin) 생성
protobuf {
    // proto 및 stub 생성을 위한 artifact 설정 begin
    protoc {
        // protobuf compiler
        artifact = "com.google.protobuf:protoc:$grpcVersion"
    }
    plugins {
        // id값이 grpc일 경우 io.grpc:protoc-gen-grpc-java artifact를 활용하여 protobuf 생성 : JAVA
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcProtoVersion"
        }
        // id값이 grpckt일 경우 io.grpc:protoc-gen-grpc-kotlin artifact를 활용하여 protobuf 생성 : KOTLIN
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk7@jar"
        }
    }
// proto 및 stub 생성을 위한 artifact 설정 end


    // protobuf 생성
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

