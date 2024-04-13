@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("imdevil.jvm.library")
    alias(libs.plugins.ksp)
}

dependencies {
    api(libs.moshi.core)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}