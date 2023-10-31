plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.database
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    // ---> Dependencies
    database()
    // ---> project-modules
    coreModels()
}