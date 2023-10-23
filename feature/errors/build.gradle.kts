import com.jefferson.bestfdjtest.buildsrc.Build
import com.jefferson.bestfdjtest.buildsrc.FdjModule

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dev.adamko.dokkatoo-html") version "1.4.0"
}

android {
    compileSdkVersion(Build.targetSdk)

    defaultConfig {
        minSdk = Build.minSdk
        targetSdk = Build.targetSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    namespace = "com.jefferson.bestfdjtest.exception"
}

dependencies {
    api(project(FdjModule.Errors.domain))
    api(project(FdjModule.Errors.core))
}
