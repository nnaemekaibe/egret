import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    id("ksp-convention")
    id("kotlin-jvm-convention")
    id("android-application-convention")
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
//            implementation(libs.connectivity.device)
//            implementation(libs.filekit.dialogs)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
//            implementation(libs.androidx.room.runtime)
//            implementation(libs.connectivity.core)

//            implementation(libs.kotlinx.serialization.protobuf)
//            implementation(libs.kotlinx.serialization.json)

//            implementation(libs.okio)


//            implementation(project(":data-core"))

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
//            implementation(libs.connectivity.http)
        }
        iosMain.dependencies {
//            implementation(libs.connectivity.device)
        }
    }
}

android {
    namespace = "com.bryan.egret"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.bryan.egret"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    val releaseSigning = if (file("debugKeystore.properties").exists()) {
        signingConfigs.create("release") {
            val props = Properties()
            props.load(FileInputStream(file("debugKeystore.properties")))
            storeFile = file(props["keystore"] as String)
            storePassword = props["keystore.password"] as String
            keyAlias = props["keyAlias"] as String
            keyPassword = props["keyPassword"] as String
        }
    } else {
        signingConfigs["debug"]
    }

    buildTypes {
        all {
            signingConfig = releaseSigning
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.bryan.egret.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.bryan.egret"
            packageVersion = "1.0.0"
        }
    }
}

configurations {
    getByName("desktopMainApi").exclude(
        group = "org.jetbrains.kotlinx",
        module = "kotlinx-coroutines-android"
    )
}