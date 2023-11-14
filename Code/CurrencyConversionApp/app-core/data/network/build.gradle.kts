import Dependencies.apiFactoryModuleLink

plugins {
    `android-library`
    `kotlin-android`
    id(Dependencies.junit5ProjectLevel) version Versions.junit5ProjectLevel
}

apply<plugins.CoreGradlePlugin>()

android {
    namespace = ProjectConfig.network
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    network()
    unitTesting()
    instrumentationTesting()

    testImplementation(project(apiFactoryModuleLink))
}