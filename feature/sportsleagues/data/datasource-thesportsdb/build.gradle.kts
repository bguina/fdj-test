import com.jefferson.bestfdjtest.buildsrc.Build
import com.jefferson.bestfdjtest.buildsrc.FdjModule
import com.jefferson.bestfdjtest.buildsrc.Libs

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

    namespace = "com.jefferson.bestfdjtest.sportsleagues.data.thesportsdb"
}

dependencies {
    api(project(FdjModule.TheSportsDbClient.api))
    implementation(project(FdjModule.SportsLeagues.core))
    implementation(Libs.Kotlin.Coroutines.android)
    implementation(Libs.timber)
    implementation(Libs.Hilt.inject)

    implementation(Libs.OkHttp.okHttp)

    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockk.mockk)
    testImplementation(Libs.JUnit.hamcrest)
    testImplementation(Libs.JUnit.hamcrestJunit)
    testImplementation(Libs.Kotlin.Coroutines.test)
}
