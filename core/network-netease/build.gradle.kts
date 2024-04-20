plugins {
    alias(libs.plugins.shot.jvm.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(project(":core:network-common"))

    implementation(libs.retrofit.core)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.retrofit.converter.moshi)

    implementation(libs.kotlinx.serialization.core.jvm)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.moshi.core)
    implementation(libs.moshi.kotlin)

    implementation(libs.kotlinx.coroutines.android)

    // should be replace
    implementation("cn.hutool:hutool-all:5.8.9")

    testImplementation(libs.junit4)
}