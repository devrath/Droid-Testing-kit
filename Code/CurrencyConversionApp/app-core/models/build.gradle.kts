plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.models
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { models() }