plugins {
    alias(libs.plugins.shot.android.library)
    alias(libs.plugins.shot.android.hilt)
}

android {
    namespace = "com.imdevil.shot.core.ui"
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    viewBinding {
        enable = true
    }
}

dependencies {
    api(project(":core:common"))

    api(libs.android.material)
    api(libs.androidx.core.ktx)
    api(libs.androidx.activity.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.constraintlayout)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.navigation.fragment.ktx)
    api(libs.androidx.navigation.ui.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.annotation)
    api(libs.androidx.preference)

    api(libs.kotlinx.coroutines.android)

    api(libs.glide.core)
    kapt(libs.glide.compiler)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}