import com.jefferson.bestfdjtest.buildsrc.Build
import com.jefferson.bestfdjtest.buildsrc.FdjModule
import com.jefferson.bestfdjtest.buildsrc.Libs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("dev.adamko.dokkatoo-html") version "1.4.0"
}

android {
    compileSdkVersion(Build.targetSdk)

    defaultConfig {
        minSdk = Build.minSdk
        targetSdk = Build.targetSdk

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += "room.schemaLocation" to "$projectDir/schemas"
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }
    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    namespace = "com.jefferson.bestfdjtest.sportsleagues.presentation"
}

dependencies {
    implementation(project(FdjModule.Errors.presentation))
    implementation(project(FdjModule.Style.api))

    implementation(project(FdjModule.SportsLeagues.api))
    implementation(project(FdjModule.SportsLeagues.TheSportsDb.api))
    implementation(project(FdjModule.SportsLeagues.Room.api))

    // Generate doc using dokkatooGenerate gradle task
    // https://github.com/adamko-dev/dokkatoo
    dokkatoo(project(FdjModule.SportsLeagues.api))
    dokkatoo(project(FdjModule.SportsLeagues.TheSportsDb.api))
    dokkatoo(project(FdjModule.SportsLeagues.Room.api))

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.runtime)
    implementation(Libs.AndroidX.Lifecycle.navigation)

    // Compose
    implementation(Libs.AndroidX.Compose.materialIconsExtended)
    implementation(Libs.AndroidX.Compose.splashscreen)
    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.AndroidX.Compose.material3)
    implementation(Libs.coilCompose)

    // Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.Hilt.worker)
    implementation(Libs.Hilt.navigation)
    kapt(Libs.Hilt.daggerCompiler)
    kapt(Libs.Hilt.hiltCompiler)

    // WorkManager
    implementation(Libs.Work.runtime)
    implementation(Libs.Work.runtimeKtx)

    // HTTP
    implementation(platform(Libs.OkHttp.bom))
    implementation(Libs.OkHttp.okHttp)
    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.moshiConverter)

    // Room
    implementation(Libs.Room.room)
    implementation(Libs.Room.kotlin)
    kapt(Libs.Room.roomCompiler)

    // Debug
    implementation(Libs.timber)

    // Test
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockk.mockk)
    testImplementation(Libs.JUnit.hamcrest)
    testImplementation(Libs.JUnit.hamcrestJunit)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.turbine)

    // AndroidTest
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
}
