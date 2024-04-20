plugins {
    alias(libs.plugins.shot.jvm.library)
    alias(libs.plugins.ksp)
}

dependencies {
    api(libs.moshi.core)
    api(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}