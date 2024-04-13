@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("imdevil.shot.library")
    id("imdevil.shot.hilt")
}

android {
    namespace = "com.imdevil.shot.feature.common"
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
    annotationProcessor(libs.glide.compiler)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}