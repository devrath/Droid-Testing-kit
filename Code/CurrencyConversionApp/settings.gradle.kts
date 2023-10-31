pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "code"
include(":app")
include(":core")
include(":core-ui")
include(":app-features")
include(":app-features:currency-converter")
include(":app-features:currency-result")
include(":app-core")
include(":app-core:data")
include(":app-core:data:network")
include(":app-core:data:database")
include(":app-core:data:preferences")
include(":app-core:common")
