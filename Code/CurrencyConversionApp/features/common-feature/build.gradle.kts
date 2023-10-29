plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.commonFeature
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { featureDependencies() }