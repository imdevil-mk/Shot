import com.imdevil.shot.BuildType

plugins {
    alias(libs.plugins.shot.android.application)
    alias(libs.plugins.shot.android.application.flavors)
    alias(libs.plugins.shot.android.hilt)
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
            applicationIdSuffix = BuildType.DEBUG.applicationIdSuffix
        }

        release {
            isMinifyEnabled = true
            applicationIdSuffix = BuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.named("debug").get()
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