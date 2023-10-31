import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    // <-------------> Top level plugin Dependencies <-------------------->
    const val hiltProjectLevel = "com.google.dagger.hilt.android"
    // <-------------> Top level plugin Dependencies <-------------------->

    const val javapoet = "com.squareup:javapoet:${Versions.javapoet}"

    // <---------------------> Core Dependencies <------------------------>
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompact = "androidx.appcompat:appcompat:${Versions.appcompact}"
    const val activitycompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val coreMaterial = "com.google.android.material:material:${Versions.coreMaterial}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val serilization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serilization}"
    // <---------------------> Core Dependencies <------------------------>


    // <---------------------> Compose Dependencies <--------------------->
    const val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.materialIconsExtended}"
    // <---------------------> Compose Dependencies <--------------------->

    // <---------------------> Hilt Dependencies <------------------------>
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    //const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    // <---------------------> Hilt Dependencies <------------------------>

    // <---------------------> OkHttp Dependencies <---------------------->
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    // <---------------------> OkHttp Dependencies <---------------------->

    // <---------------------> Retrofit Dependencies <-------------------->
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val kotlinxSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinxSerializationConverter}"
    const val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationJson}"
    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2ConverterGson}"
    // <---------------------> Retrofit Dependencies <-------------------->

    // <---------------------> Room Dependencies <------------------------>
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    // <---------------------> Room Dependencies <------------------------>

    // <---------------------> UnitTest Dependencies <-------------------->
    const val junit = "junit:junit:${Versions.junit}"
    // <---------------------> UnitTest Dependencies <-------------------->

    // <--------------> InstrumentationTest Dependencies <---------------->
    const val junitInstrumentation = "androidx.test.ext:junit:${Versions.junitInstrument}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    // <--------------> InstrumentationTest Dependencies <---------------->

}

// <----------- Individual Library group Dependencies ---------------->
// <--------> Core Dependencies <---------------->
fun DependencyHandler.coreDependencies() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activitycompose)
    implementation(Dependencies.appcompact)
    implementation(Dependencies.coreMaterial)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.serilization)
    hilt()
}
// <--------> Room Dependencies <---------------->
fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}
// <--------> Retrofit Dependencies <------------>
fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.kotlinxSerializationConverter)
    implementation(Dependencies.kotlinxSerializationJson)
    implementation(Dependencies.retrofit2ConverterGson)
}
// <--------> Compose Dependencies <------------->
fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.materialIconsExtended)
    debugImplementation(Dependencies.composeUiToolingPreview)
}
// <---> Unit Testing Dependencies <---------------->
fun DependencyHandler.unitTesting() {
    testImplementation(Dependencies.junit)
}
// <---> Instrumentation Testing Dependencies <----->
fun DependencyHandler.instrumentationTesting() {
    implementation(Dependencies.junitInstrumentation)
    implementation(Dependencies.espressoCore)
}
// <--------> Hilt Dependencies <------------------->
fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}
// <----------- Individual Library group Dependencies ---------------->



// <----------------- Root Module Dependencies ----------------------->
// <-----> App Dependencies <----->
fun DependencyHandler.appModuleDependencies() {
    coreDependencies()
    compose()
    unitTesting()
    instrumentationTesting()
    // --> Single modules
    common()
}
// <-----> Core Dependencies <----->
fun DependencyHandler.coreModuleDependencies() {
    coreDependencies()
    unitTesting()
    room()
    retrofit()
}
// <-----> Data Dependencies <----->
fun DependencyHandler.dataModuleDependencies() {
    coreDependencies()
    unitTesting()
    room()
    retrofit()
    // --> Single modules
    common()
}
// <-----> Ui Dependencies <------->
fun DependencyHandler.uiModuleDependencies() {
    coreDependencies()
    instrumentationTesting()
    unitTesting()
    compose()
    // --> Single modules
    coreModelsModule()
}
// <----> Feature Dependencies <---->
fun DependencyHandler.featureDependencies() {
    coreDependencies()
    instrumentationTesting()
    unitTesting()
    compose()
    implementation(Dependencies.hiltNavigationCompose)
    // --> Single modules
    common()
    coreUiModule()
}
// <----------------- Root Module Dependencies ----------------------->

// <----------------- App-Core Module Dependencies ------------------->
// <----> Network Module Dependencies <---->
fun DependencyHandler.network() {
    coreDependencies()
    unitTesting()
    retrofit()
    // --> Single modules
    common()
    coreModelsModule()
}
// <----> Database Module Dependencies <---->
fun DependencyHandler.database() {
    coreDependencies()
    unitTesting()
    room()
    // --> Single modules
    common()
    coreModelsModule()
}
// <---> Preferences Module Dependencies <--->
fun DependencyHandler.preferences() {
    coreDependencies()
    unitTesting()
    room()
    // --> Single modules
    common()
}
// <---> Common Module Dependencies <--->
fun DependencyHandler.common() {
    coreDependencies()
    retrofit()
    room()
    // --> Single modules
    coreModelsModule()
}
// <---> Models Module Dependencies <--->
fun DependencyHandler.models() {
    coreDependencies()
    retrofit()
    room()
}
// <----------------- App-Core Module Dependencies ------------------->

// <----------------- Feature Module Dependencies ------------------->
// <---> CurrencyConversion Feature Module Dependencies <--->
fun DependencyHandler.currencyConverterFeature() {
    featureDependencies()
    retrofit()
    // --> Single modules
    coreModelsModule()
    appCommonModule()
    appNetworkModule()
    appDatabaseModlue()
}
// <---> CurrencyResult Feature Module Dependencies <--->
fun DependencyHandler.currencyResultFeature() {
    featureDependencies()
}
// <----------------- Feature Module Dependencies ------------------->


// <------------------------ Project Modules ------------------------>
// <-----------> Feature-Modules <------------>
fun DependencyHandler.features() { implementation(project(":app-features")) }
fun DependencyHandler.featureCurrencyConverter() { implementation(project(":app-features:currency-converter")) }
fun DependencyHandler.featureCurrencyResult() { implementation(project(":app-features:currency-result")) }
// <-----------> Feature-Modules <------------->
// <-----------> Container-Modules <----------->
fun DependencyHandler.appCore() { implementation(project(":app-core")) }
fun DependencyHandler.appData() { implementation(project(":app-core:app-data")) }
// <-----------> Container-Modules <----------->
// <-----------> Core-Modules <----------------->
fun DependencyHandler.appNetworkModule() { implementation(project(":app-core:data:network")) }
fun DependencyHandler.appDatabaseModlue() { implementation(project(":app-core:data:database")) }
fun DependencyHandler.appPreferencesModule() { implementation(project(":app-core:data:preferences")) }
fun DependencyHandler.appCommonModule() { implementation(project(":app-core:common")) }
fun DependencyHandler.coreUiModule() { implementation(project(":app-core:ui")) }
fun DependencyHandler.coreModelsModule() { implementation(project(":app-core:models")) }
// <-----------> Core-Modules <----------------->
// <------------------------ Project Modules ------------------------>