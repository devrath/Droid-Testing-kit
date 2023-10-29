plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.MainUiGradlePlugin>()

android {
    namespace = ProjectConfig.currencyResult
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { featureDependencies() }