plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Tambahkan baris ini
    alias(libs.plugins.kotlin.compose) apply false
}