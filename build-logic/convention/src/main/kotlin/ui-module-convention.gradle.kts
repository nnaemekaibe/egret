import ext.configureCompose
import ext.configureKotlinMultiplatform
import ext.configureUiModule

plugins{
    kotlin("multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
}

configureCompose()

kotlin{
    configureKotlinMultiplatform(this)
    configureUiModule(this)
}
