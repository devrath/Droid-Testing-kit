plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.istudio.core_ui"
    kotlinOptions { jvmTarget = "18" }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = Versions.composeCompiler }
}

dependencies { uiModuleDependencies() }