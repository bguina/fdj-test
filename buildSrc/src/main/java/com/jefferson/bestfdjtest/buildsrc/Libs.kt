package com.jefferson.bestfdjtest.buildsrc

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:8.0.2"

    object Kotlin {
        const val version = "1.8.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Coroutines {
            private const val version = "1.7.0"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    const val timber = "com.jakewharton.timber:timber:5.0.1"

    object OkHttp {
        private const val version = "4.11.0"
        const val bom = "com.squareup.okhttp3:okhttp-bom:$version"
        const val okHttp = "com.squareup.okhttp3:okhttp"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
    }

    object Room {
        private const val version = "2.5.1"

        const val room = "androidx.room:room-runtime:$version"
        const val roomCompiler = "androidx.room:room-compiler:$version"
        const val kotlin = "androidx.room:room-ktx:$version"
    }

    object Work {
        private const val version = "2.8.1"

        // (Java only)
        const val runtime = "androidx.work:work-runtime:$version"

        // Kotlin + coroutines
        const val runtimeKtx = "androidx.work:work-runtime-ktx:$version"
    }

    object Moshi {
        const val version = "1.14.0"
        const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
        const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }


    object AndroidX {
        object Compose {
            const val version = "1.4.3"

            const val runtime = "androidx.compose.runtime:runtime:$version"

            const val material3 = "androidx.compose.material3:material3:1.1.0"
            const val splashscreen = "androidx.core:core-splashscreen:1.0.0"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val compiler = "androidx.compose.compiler:compiler:1.1.0"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
        }

        object Lifecycle {
            private const val version = "2.6.0"

            /**
             * https://androidx.tech/artifacts/navigation/navigation-compose/
             * */
            const val runtime = "androidx.lifecycle:lifecycle-runtime-compose:$version"
            const val navigation = "androidx.navigation:navigation-compose:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:2.6.1"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val coreKt = "androidx.test:core-ktx:$version"
            const val runner = "androidx.test:runner:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.5"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
            const val uiAutomator = "androidx.test.uiautomator:uiautomator:2.2.0"
        }
    }

    const val coilCompose = "io.coil-kt:coil-compose:2.4.0"

    object Hilt {
        /**
         * https://dagger.dev/
         * */
        private const val version = "2.46.1"

        const val inject = "javax.inject:javax.inject:1"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val worker = "androidx.hilt:hilt-work:1.0.0"
        const val daggerCompiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"

        /**
         * https://developer.android.com/jetpack/androidx/releases/hilt
         * */
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
        const val hamcrest = "org.hamcrest:hamcrest:2.2"
        const val hamcrestJunit = "org.hamcrest:hamcrest-junit:2.0.0.0"
    }

    object Mockk {
        private const val version = "1.13.5"
        const val mockk = "io.mockk:mockk:$version"
        const val mockkAndroid = "io.mockk:mockk-android:$version"
        const val mockkAndroidAgent = "io.mockk:mockk-agent-jvm:$version"
    }

    const val turbine = "app.cash.turbine:turbine:0.13.0"
}
