pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}



include(":thesportsdb-client")
include(":style")
include(":app")

include(":feature")
apply {
    from("feature/errors/settings.gradle.kts")
    from("feature/sportsleagues/settings.gradle.kts")
}

rootProject.name = "BestFdjTest"
