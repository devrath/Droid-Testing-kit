plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.preferences
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies { preferences() }