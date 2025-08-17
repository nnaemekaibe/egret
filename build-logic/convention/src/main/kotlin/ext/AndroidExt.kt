
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies

fun Project.commonConfiguration(
    extension: CommonExtension<*, *, *, *, *, *>,
) = extension.apply {
    compileSdk = 36

    defaultConfig {
        // Could have been 21, but I need sqlite 3.24.0 for upserts
        minSdk = 30
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = ProjectJavaVersion
        targetCompatibility = ProjectJavaVersion
    }
    configureKotlinJvm()
}


fun Project.addDesugarDependencies() {
    dependencies {
        add(
            configurationName = "coreLibraryDesugaring",
            dependencyNotation = versionCatalog.findLibrary("android-desugarJdkLibs").get()
        )
    }
}

val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")