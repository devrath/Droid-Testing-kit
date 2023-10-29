plugins {
    `android-library`
    `kotlin-android`
}

apply<MainUiGradlePlugin>()

android {
    namespace = ProjectConfig.currencyConverter
    kotlinOptions { jvmTarget = "18" }
}

dependencies { uiModuleDependencies() }