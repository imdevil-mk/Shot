plugins {
    alias(libs.plugins.shot.android.library)
    alias(libs.plugins.shot.android.hilt)
}

android {
    namespace = "com.imdevil.shot.network.tencent"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
}

dependencies {
    api(project(":core:network-common"))

    testImplementation(libs.junit4)
}