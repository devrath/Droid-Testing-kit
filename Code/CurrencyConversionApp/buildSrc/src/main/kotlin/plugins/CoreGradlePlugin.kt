package plugins

import ProjectConfig
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CoreGradlePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
            plugin("kotlin-kapt")
            plugin("dagger.hilt.android.plugin")
        }
    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
            compileSdk = ProjectConfig.compileSdk
            buildFeatures.buildConfig = true
            buildTypes {
                release {
                    buildConfigField("String", "BASE_URL", ProjectConfig.baseUrl)
                    buildConfigField("String", "API_KEY", ProjectConfig.apiKey)
                }
                debug {
                    buildConfigField("String", "BASE_URL", ProjectConfig.baseUrl)
                    buildConfigField("String", "API_KEY", ProjectConfig.apiKey)
                }
            }

            defaultConfig {
                minSdk = ProjectConfig.minSdk
                testInstrumentationRunner = ProjectConfig.testInstrumentationRunner_AndroidJUnitRunner
            }

            compileOptions {
                sourceCompatibility = ProjectConfig.javaVersion
                targetCompatibility = ProjectConfig.javaVersion
            }
        }
    }

    private fun Project.android(): LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }
}