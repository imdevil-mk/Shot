plugins {
    id("imdevil.shot.library")
    id("imdevil.shot.hilt")
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