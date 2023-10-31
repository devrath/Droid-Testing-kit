plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.common
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { common() }