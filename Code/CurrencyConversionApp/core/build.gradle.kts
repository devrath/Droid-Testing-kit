plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.CoreGradlePlugin>()

android {
    namespace = ProjectConfig.core
}

dependencies {
    coreModuleDependencies()
}