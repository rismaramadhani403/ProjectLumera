plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.hilt)
}

kapt {
    correctErrorTypes = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
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

    implementation("androidx.compose.material:material-icons-extended")

    // ViewModel Compose
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2"
    )

    // DataStore Preferences
    implementation(
        "androidx.datastore:datastore-preferences:1.1.1"
    )

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.hilt.navigation.compose)

    // ROOM DATABASE
    implementation("androidx.room:room-runtime:2.7.2")
    implementation("androidx.room:room-ktx:2.7.2")
    kapt("androidx.room:room-compiler:2.7.2")

    // RETROFIT (koneksi ke API PHP) - Week 11
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // COIL (load gambar menu dari URL server, bukan drawable lokal lagi)
    implementation("io.coil-kt:coil-compose:2.6.0")
}