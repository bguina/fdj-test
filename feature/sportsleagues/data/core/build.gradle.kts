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

    namespace = "com.jefferson.bestfdjtest.sportsleagues.data.core"
}

dependencies {
    api(project(FdjModule.Errors.domain))
    implementation(project(FdjModule.SportsLeagues.domain))

    implementation(Libs.Kotlin.Coroutines.android)
    implementation(Libs.timber)

    // WorkManager
    implementation(Libs.Work.runtime)
    implementation(Libs.Work.runtimeKtx)
    implementation(Libs.AndroidX.Lifecycle.livedata)

    // Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.Hilt.worker)
    kapt(Libs.Hilt.daggerCompiler)
    kapt(Libs.Hilt.hiltCompiler)

    // Test
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockk.mockk)
    testImplementation(Libs.JUnit.hamcrest)
    testImplementation(Libs.JUnit.hamcrestJunit)
    testImplementation(Libs.Kotlin.Coroutines.test)
}
