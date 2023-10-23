import com.jefferson.bestfdjtest.buildsrc.FdjModule
import com.jefferson.bestfdjtest.buildsrc.Libs

plugins {
    id("kotlin")
    id("kotlin-kapt")
    id("dev.adamko.dokkatoo-html") version "1.4.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(FdjModule.Errors.domain))

    implementation(Libs.Hilt.inject)
    implementation(Libs.Kotlin.Coroutines.android)

    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockk.mockk)
    testImplementation(Libs.JUnit.hamcrest)
    testImplementation(Libs.JUnit.hamcrestJunit)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.turbine)
}
