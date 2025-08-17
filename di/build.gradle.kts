plugins {
    id("android-library-convention")
    id("kotlin-library-convention")
    id("ksp-convention")
}

android {
    namespace = "com.bryan.egret.di"
}

kotlin{
    sourceSets{
        commonTest{
            dependencies{
                implementation(kotlin("test"))
            }
        }
    }
}