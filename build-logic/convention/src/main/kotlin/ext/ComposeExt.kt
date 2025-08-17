package ext

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin

fun Project.configureCompose(){
    extensions.configure<ComposeCompilerGradlePluginExtension>{
        stabilityConfigurationFiles.addAll(rootProject.layout.projectDirectory.file("compose_compiler_config.conf"))
    }
}