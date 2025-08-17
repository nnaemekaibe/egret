package ext

import configureKotlinJvm
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import versionCatalog

fun Project.configureKotlinMultiplatform(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension
){
    kotlinMultiplatformExtension.apply {
        androidTarget()
        jvm("desktop")
        iosX64()
        iosArm64()
        iosSimulatorArm64()

        sourceSets.apply {
            all {
                languageSettings.apply{
                    optIn("androidx.compose.animation.ExperimentalAnimationApi")
                    optIn("androidx.compose.foundation.ExperimentalFoundationApi")
                    optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                    optIn("androidx.compose.material.ExperimentalMaterialApi")
                    optIn("androidx.compose.ui.ExperimentalComposeUiApi")
                    optIn("kotlinx.serialization.ExperimentalSerializationApi")
                    optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                    optIn("kotlinx.coroutines.FlowPreview")
                }
            }

            named("commonMain"){
                dependencies {
                    implementation(versionCatalog.findLibrary("compose-runtime").get())
                }
            }
        }

        configureKotlinJvm()
    }

}