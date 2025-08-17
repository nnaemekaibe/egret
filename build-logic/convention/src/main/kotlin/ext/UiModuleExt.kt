package ext

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureUiModule(
    extension: KotlinMultiplatformExtension
) = extension.apply{
    sourceSets.apply {
        named("commonMain"){
            dependencies{

            }
        }

        named("androidMain"){
            dependencies{

            }
        }

        named("desktopMain"){
            dependencies{

            }
        }

    }
}