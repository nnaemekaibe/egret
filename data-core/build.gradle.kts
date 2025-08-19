plugins{
    id("android-library-convention")
    id("kotlin-library-convention")
    id("ksp-convention")
    kotlin("plugin.serialization")
}

android{
    namespace = "com.bryan.egret.data"
}


kotlin{
    sourceSets{
        commonMain{
            dependencies{
                api(project(":di"))
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization.cbor)
                implementation(libs.kotlinx.serialization.protobuf)

                implementation(libs.ktor.client.core)
            }
        }
        commonTest{
            dependencies{
                implementation(libs.kotlin.test)
            }
        }
    }
}

dependencies{
    implementation(project(":di"))
}