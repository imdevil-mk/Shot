plugins {
    alias(libs.plugins.shot.android.application)
}

android {
    defaultConfig {
        applicationId = "com.imdevil.shot"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }

        val release by getting {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    namespace = "com.imdevil.shot"
}

dependencies {
    api(project(":feature:common"))
    api(project(":feature:settings"))
    api(project(":feature:tencent"))

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
}