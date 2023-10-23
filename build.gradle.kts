// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.jefferson.bestfdjtest.buildsrc.Libs.androidGradlePlugin)
        classpath(com.jefferson.bestfdjtest.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.jefferson.bestfdjtest.buildsrc.Libs.Hilt.gradlePlugin)
    }
}

plugins {
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("dev.adamko.dokkatoo-html") version "1.4.0"
    id("dev.adamko.dokkatoo-javadoc") version "1.4.0"
}

subprojects {

    repositories {
        google()
        mavenCentral()
    }

    val kotlinVersion by lazy {
        plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper>()
            .map { it.pluginVersion }.distinct().single()
    }
    configurations.forEach { config ->
        config.resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin" && requested.name.startsWith("kotlin-")) {
                useVersion(kotlinVersion)
            }
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "11"
    }
}
