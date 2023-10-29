plugins {
    `android-library`
    `kotlin-android`
}

apply<MainUiGradlePlugin>()

android {
    namespace = "com.istudio.core_ui"
    kotlinOptions { jvmTarget = "18" }
}

dependencies { uiModuleDependencies() }