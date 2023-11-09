import org.gradle.api.JavaVersion

object ProjectConfig {

    const val minSdk = 24
    const val compileSdk = 34
    const val targetSdk = 34
    const val versionCode = 1
    const val versionName = "1.0"
    // <---------> Java Version <--------->
    const val jvmTarget = "18"
    val javaVersion = JavaVersion.VERSION_18
    // <---------> Java Version <--------->

    // <---------> Namespaces <--------->
    const val appId = "com.istudio.code"
    const val core = "com.istudio.core"
    const val coreUi = "com.istudio.core_ui"
    const val currencyConverter = "com.istudio.currency_converter"
    const val currencyResult = "com.istudio.currency_result"
    const val commonFeature = "com.istudio.common_feature"
    const val models = "com.istudio.models"
    const val fakes = "com.istudio.fakes"


    const val network = "com.istudio.network"
    const val database = "com.istudio.database"
    const val preferences = "com.istudio.preferences"
    const val mockFactory = "com.istudio.mock_factory"
    const val testutils = "com.istudio.test_utilities"

    const val common = "com.istudio.common"
    // <---------> Namespaces <--------->

    // <---------> Test Instrumentaiton <--------->
    const val testInstrumentationRunner_AndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
    // <---------> Test Instrumentaiton <--------->

    // <---------> End-Point Configs <------------>
    val baseUrl = "\"https://openexchangerates.org/api\""
    val apiKey = "\"e41276934dea4babb34536dad2f2da7a\""
    // <---------> End-Point Configs <------------>

}