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
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
}