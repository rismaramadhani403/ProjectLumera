plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Jetpack Compose
    alias(libs.plugins.kotlin.compose)
}

android {

    namespace = "com.praktikum.lumera"
    compileSdk = 35

    defaultConfig {

        applicationId = "com.praktikum.lumera"
        minSdk = 24
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }

    buildFeatures {

        compose = true
    }

    packaging {

        resources {

            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)

    implementation(
        platform(libs.androidx.compose.bom)
    )

    implementation(libs.androidx.ui)

    implementation(libs.androidx.ui.graphics)

    implementation(libs.androidx.ui.tooling.preview)

    implementation(libs.androidx.material3)

    implementation(libs.androidx.navigation.compose)

    // ViewModel Compose
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2"
    )

    // DataStore Preferences
    implementation(
        "androidx.datastore:datastore-preferences:1.1.1"
    )
}