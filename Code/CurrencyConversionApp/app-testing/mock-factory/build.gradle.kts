plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.mockFactory
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    coreModelsModule()
    database()
    retrofit()
}