plugins {
    `kotlin-dsl`
}

group = "com.bryan.egret.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.compose.compiler.plugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.serializationPlugin)
    implementation(libs.google.devtools.kspPlugin)
    implementation(libs.metro.gradlePlugin)
}