plugins {
    `android-library`
    `kotlin-android`
}

apply<MainUiGradlePlugin>()

android {
    namespace = "com.istudio.currency_converter"
    kotlinOptions { jvmTarget = "18" }
}

dependencies { featureDependencies() }