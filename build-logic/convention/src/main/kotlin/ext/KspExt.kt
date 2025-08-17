import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureKsp(){
    extensions.configure<KotlinMultiplatformExtension>{
        targets.configureEach{

        }
    }
}