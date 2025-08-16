rootProject.name = "Egret"
enableFeaturePreview("STABLE_CONFIGURATION_CACHE") //Opts into Gradle configuration cache for faster builds.
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(
    ":composeApp",
    ":data",
    ":data-core",
    ":data-database",
    ":di",
    ":scaffold",
    ":feature-auth",
    ":feature-compose",
    ":feature-conversation",
    ":feature-feed",
    ":feature-gallery",
    ":feature-home",
    ":feature-list",
    ":feature-messages",
    ":feature-notifications",
    ":feature-post-detail",
    ":feature-profile",
    ":feature-profile-avatar",
    ":feature-profiles",
    ":feature-search",
    ":feature-splash",
    ":feature-template",
    ":ui-core",
    ":ui-media",
    ":ui-tiling", //duplicate remove later
    ":ui-timeline",
)
