@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("imdevil.jvm.library")
}

dependencies {
    api(libs.retrofit.core)
    api(libs.okhttp.logging)
    api(libs.retrofit.kotlin.serialization)
    api(libs.retrofit.converter.moshi)

    api(libs.moshi.core)
    api(libs.moshi.kotlin)
}