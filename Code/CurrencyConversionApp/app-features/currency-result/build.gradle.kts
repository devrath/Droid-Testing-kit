plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.currencyResult
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    currencyResultFeature()
    // --> Single modules
    coreModelsModule()
    appCommonModule()
}