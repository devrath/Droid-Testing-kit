plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.CoreGradlePlugin>()

android {
    namespace = ProjectConfig.common
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    // ---> Dependencies
    common()
    // ---> project-modules
    coreModels()
}