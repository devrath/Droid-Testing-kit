import Dependencies.mockFactoryModuleLink

plugins {
    `android-library`
    `kotlin-android`
    id(Dependencies.junit5ProjectLevel) version Versions.junit5ProjectLevel
}

apply<plugins.FeaturesGradlePlugin>()

android {
    namespace = ProjectConfig.currencyConverter
    kotlinOptions { ProjectConfig.jvmTarget }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    currencyConverterFeature()
    appPreferencesModule()
    testImplementation(project(mockFactoryModuleLink))
    //implementation(project(mapOf("path" to ":app-core:data:preferences")))
}