import Dependencies.apiFactoryModuleLink

plugins {
    `android-library`
    `kotlin-android`
     id(Dependencies.junit5ProjectLevel) version Versions.junit5ProjectLevel
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.database
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {
    database()

    // Utilize the mocks from a mock module
    androidTestImplementation(project(apiFactoryModuleLink))
}