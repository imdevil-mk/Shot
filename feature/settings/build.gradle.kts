plugins {
    alias(libs.plugins.shot.android.library)
    alias(libs.plugins.shot.android.hilt)
}

android {
    namespace = "com.imdevil.shot.feature.settings"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    viewBinding {
        enable = true
    }
}

dependencies {
    api(project(":core:data"))
    api(project(":feature:common"))

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}