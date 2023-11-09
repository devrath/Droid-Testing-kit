plugins {
    `android-library`
    `kotlin-android`
    id(Dependencies.junit5ProjectLevel) version Versions.junit5ProjectLevel
}

apply<plugins.TestingUtilitiesGradlePlugin>()

android {
    namespace = ProjectConfig.testutils
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {

    // <-- Modules where this mock dependencies are shared-->
    appDatabaseModlue()
}