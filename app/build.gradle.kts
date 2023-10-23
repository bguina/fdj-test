import com.jefferson.bestfdjtest.buildsrc.Build
import com.jefferson.bestfdjtest.buildsrc.FdjModule
import com.jefferson.bestfdjtest.buildsrc.Libs

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("dev.adamko.dokkatoo-html") version "1.4.0"
}

android {
    compileSdk = Build.targetSdk

    defaultConfig {
        applicationId = "com.jeffersion.bestfdjtest.android"
        minSdk = Build.minSdk
        targetSdk = Build.targetSdk
        versionCode = Build.versionCode
        versionName = Build.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.AndroidX.Compose.version
    }

    namespace = "com.jefferson.bestfdjtest.android"
}

dependencies {
    implementation(project(FdjModule.SportsLeagues.presentation))
    implementation(project(FdjModule.Style.api))

    // Generate doc using dokkatooGenerate gradle task
    // https://github.com/adamko-dev/dokkatoo
    dokkatoo(project(FdjModule.Errors.presentation))
    dokkatoo(project(FdjModule.SportsLeagues.presentation))
    dokkatoo(project(FdjModule.Style.api))
    dokkatoo(project(FdjModule.TheSportsDbClient.api))

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.runtime)

    // Compose
    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.splashscreen)
    implementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.toolingPreview)
    implementation(Libs.coilCompose)

    // Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.Hilt.worker)
    implementation(Libs.Hilt.navigation)
    kapt(Libs.Hilt.daggerCompiler)

    // WorkManager
    implementation(Libs.Work.runtime)
    implementation(Libs.Work.runtimeKtx)
    kapt(Libs.Hilt.hiltCompiler)

    // HTTP
    implementation(platform(Libs.OkHttp.bom))
    implementation(Libs.OkHttp.okHttp)
    implementation(Libs.OkHttp.loggingInterceptor)

    // Debug
    implementation(Libs.timber)
    debugImplementation(Libs.AndroidX.Compose.tooling)

    // Test
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.JUnit.hamcrest)

    // AndroidTest
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)
    androidTestImplementation(Libs.AndroidX.Compose.uiTest)
}
