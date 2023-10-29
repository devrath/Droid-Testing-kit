package plugins

import ProjectConfig
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class CoreBuildTypesPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        setProjectConfig(project)
    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
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
        }
    }

    private fun Project.android(): LibraryExtension {
        return extensions.getByType(LibraryExtension::class.java)
    }
}