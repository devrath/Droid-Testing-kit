plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.currencyConverter
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { currencyConverterFeature() }