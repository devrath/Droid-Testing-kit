plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

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