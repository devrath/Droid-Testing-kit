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
    coreModelsModule()

    // <-- Modules where this mock dependencies are shared-->
    //appDatabaseModlue()
}