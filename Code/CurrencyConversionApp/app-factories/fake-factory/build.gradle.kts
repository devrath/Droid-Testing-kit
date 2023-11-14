import Dependencies.apiFactoryModuleLink

plugins {
    `android-library`
    `kotlin-android`
}

apply<plugins.TestingUtilitiesGradlePlugin>()

android {
    namespace = ProjectConfig.fakeFactory
    kotlinOptions { ProjectConfig.jvmTarget }
}

dependencies {

    implementation(Dependencies.kotlinxCoroutinesCore)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.gson)

    hilt()

    // models
    coreModelsModule()

    // Project link to API - source
    implementation(project(apiFactoryModuleLink))
    // Project link to Network module
    appNetworkModule()
    // Project link to Database module
    appDatabaseModlue()
    // Project link to common module
    appCommonModule()

    // Feature - Currency converter feature


    // <-- Modules where this Fakes are shared-->
    featureCurrencyConverter()
}