import ext.configureKotlinMultiplatform

plugins{
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
}

kotlin{
    configureKotlinMultiplatform(this)
}