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
    namespace = "com.jefferson.bestfdjtest.exception.presentation"
}

dependencies {
    implementation(project(FdjModule.Style.api))

    implementation(project(FdjModule.Errors.api))

    // Generate doc using dokkatooGenerate gradle task
    // https://github.com/adamko-dev/dokkatoo
    dokkatoo(project(FdjModule.Errors.api))

    // Lifecycle
    implementation(Libs.AndroidX.Lifecycle.runtime)
    implementation(Libs.AndroidX.Lifecycle.navigation)

    // Compose
    implementation(Libs.AndroidX.Compose.material3)
    implementation(Libs.AndroidX.Compose.compiler)
    implementation(Libs.AndroidX.Compose.tooling)
    implementation(Libs.AndroidX.Compose.toolingPreview)

    // Hilt
    implementation(Libs.Hilt.android)
    implementation(Libs.Hilt.navigation)
    kapt(Libs.Hilt.daggerCompiler)
    kapt(Libs.Hilt.hiltCompiler)

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
