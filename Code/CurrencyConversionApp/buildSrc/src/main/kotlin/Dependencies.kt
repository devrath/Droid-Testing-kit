import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
    // <-------------> Top level plugin Dependencies <-------------------->
    const val hiltProjectLevel = "com.google.dagger.hilt.android"
    const val serializationProjectLevel = "org.jetbrains.kotlin.plugin.serialization"
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
fun DependencyHandler.coreDependencies() {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.activitycompose)
    implementation(Dependencies.appcompact)
    implementation(Dependencies.coreMaterial)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.serilization)
    hilt()
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.materialIconsExtended)
    debugImplementation(Dependencies.composeUiToolingPreview)
}

fun DependencyHandler.unitTesting() {
    testImplementation(Dependencies.junit)
}

fun DependencyHandler.instrumentationTesting() {
    implementation(Dependencies.junitInstrumentation)
    implementation(Dependencies.espressoCore)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}
// <----------- Individual Library group Dependencies ---------------->



// <----------------- Root Module Dependencies ----------------------->
fun DependencyHandler.appModuleDependencies() {
    coreDependencies()
    compose()
    unitTesting()
    instrumentationTesting()
    // --> Single modules
    core()
}

fun DependencyHandler.coreModuleDependencies() {
    coreDependencies()
    unitTesting()
    room()
    retrofit()
    implementation(Dependencies.kotlinxSerializationConverter)
}

fun DependencyHandler.dataModuleDependencies() {
    coreDependencies()
    unitTesting()
    room()
    retrofit()
    // --> Single modules
    core()
}

fun DependencyHandler.uiModuleDependencies() {
    coreDependencies()
    instrumentationTesting()
    unitTesting()
    compose()
}

fun DependencyHandler.featureDependencies() {
    coreDependencies()
    instrumentationTesting()
    unitTesting()
    compose()
    implementation(Dependencies.hiltNavigationCompose)
    // --> Single modules
    core()
    coreUi()
}
// <----------------- Root Module Dependencies ----------------------->


// <------------------------ Project Modules ------------------------>
fun DependencyHandler.core() { implementation(project(":core")) }
fun DependencyHandler.coreUi() { implementation(project(":core-ui")) }
fun DependencyHandler.features() { implementation(project(":features")) }
fun DependencyHandler.featureCurrencyConverter() { implementation(project(":features:currency-converter")) }
fun DependencyHandler.featureCurrencyResult() { implementation(project(":features:currency-result")) }
fun DependencyHandler.featureCommon() { implementation(project(":features:common-feature")) }
// <------------------------ Project Modules ------------------------>
