plugins {
    alias(libs.plugins.shot.jvm.library)
}

dependencies {
    api(libs.retrofit.core)
    api(libs.okhttp.logging)
    api(libs.retrofit.kotlin.serialization)
    api(libs.retrofit.converter.moshi)

    api(libs.moshi.core)
    api(libs.moshi.kotlin)
}