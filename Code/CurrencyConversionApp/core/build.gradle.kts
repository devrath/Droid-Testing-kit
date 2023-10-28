plugins {
    `android-library`
    `kotlin-android`
}

apply<MainGradlePlugin>()

android {
    namespace = "com.istudio.core"

    android.buildFeatures.buildConfig = true

    val baseUrl = "\"https://openexchangerates.org/api\""
    val apiKey = "\"e41276934dea4babb34536dad2f2da7a\""

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", baseUrl)
            buildConfigField("String", "API_KEY", apiKey)
        }
        debug {
            buildConfigField("String", "BASE_URL", baseUrl)
            buildConfigField("String", "API_KEY", apiKey)
        }
    }
}

dependencies {
    coreModuleDependencies()
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
}