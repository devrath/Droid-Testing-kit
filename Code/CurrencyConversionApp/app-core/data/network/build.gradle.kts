import Dependencies.mockFactoryModuleLink

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
    network()
    unitTesting()
    instrumentationTesting()
    testImplementation(project(mockFactoryModuleLink))
}