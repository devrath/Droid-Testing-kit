plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.istudio.currency_converter"
}

dependencies { featureDependencies() }