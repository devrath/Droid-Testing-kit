plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.CoreGradlePlugin>()

android {
    namespace = ProjectConfig.network
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    // ---> Dependencies
    network()
    // ---> project-modules
    coreModels()
}