plugins {
    alias(libs.plugins.shot.android.library)
    alias(libs.plugins.shot.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.imdevil.shot.network.tencent"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    sourceSets.getByName("test") {
        resources.setSrcDirs(listOf("../../z_data"))
    }
}

dependencies {
    api(project(":core:network-common"))
    ksp(libs.moshi.kotlin.codegen)

    testImplementation(project(":core:model"))
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(libs.retrofit.mock)
    testImplementation(libs.junit4)
    testImplementation(libs.kotlinx.coroutines.test)
}