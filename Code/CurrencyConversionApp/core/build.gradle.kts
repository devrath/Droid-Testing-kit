plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()
apply<BuildTypesPlugin>()

android {
    namespace = ProjectConfig.core
}

dependencies {
    coreModuleDependencies()
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
}