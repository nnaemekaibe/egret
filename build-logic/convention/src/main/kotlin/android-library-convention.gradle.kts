import ext.libs

plugins{
    id("com.android.library")
}

android{
    commonConfiguration(this)
    defaultConfig{
        lint.targetSdk = libs.findVersion("android-targetSdk").get().toString().toInt()

    }

    sourceSets {
        named("main"){
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}

addDesugarDependencies()


