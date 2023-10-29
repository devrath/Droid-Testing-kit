plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.MainUiGradlePlugin>()

android {
    namespace = ProjectConfig.currencyConverter
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { featureDependencies() }