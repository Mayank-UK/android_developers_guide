package com.androiddevelopersguide.ui.screens.dependencymanagement

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DependencyManagementScreen(
    navController: NavController,
    viewModel: DependencyManagementViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Build tools & dependencies",
        mobileUI = {
            Introduction()
            Gradle()
            VersionCatalog()
            BuildFlavors()
            BuildConfig()
            KspVsKapt()
        }
    )
}

@Composable
fun Introduction() {
    Section(
        title = "What & why",
        description = """
            Gradle is the build system Android Studio ships with. Two jobs dominate day-to-day Android work: declaring what your app depends on (and at which versions), and producing different builds of the same app for different environments (dev, staging, prod) from a single source tree. Getting both right early removes a whole class of "works on my machine" pain: every developer and every CI build produces the same APK, and switching between environments is a build-field decision, not a code change.
        """.trimIndent(),
    )
}

@Composable
fun Gradle() {
    Section(
        title = "Gradle & the two build files",
        description = """
            An Android project has two Gradle files that matter. The project-level build.gradle.kts declares the plugins applied across modules (the Android application plugin, Kotlin, Hilt, KSP, the Compose compiler). The module-level app/build.gradle.kts is where the android { } block lives: SDK versions, the applicationId, signing, flavors, and the dependencies { } block.

            This app uses the Kotlin DSL (.kts) so the build file is plain Kotlin with IDE autocomplete and type-checking, rather than Groovy.
        """.trimIndent(),
        code = """
            // app/build.gradle.kts — the android { } block (abbreviated)
            android {
                namespace = "com.androiddevelopersguide"
                compileSdk = 34
                defaultConfig {
                    applicationId = "com.androiddevelopersguide"
                    minSdk = 24
                    targetSdk = 34
                    versionCode = 1
                    versionName = "1.0"
                }
                buildFeatures {
                    compose = true
                    buildConfig = true     // generates the BuildConfig class
                }
            }
        """.trimIndent()
    )
}

@Composable
fun VersionCatalog() {
    Section(
        title = "The version catalog (libs.versions.toml)",
        description = """
            Dependencies and versions are declared once, in gradle/libs.versions.toml, and accessed from the build file through generated type-safe accessors (libs.retrofit, libs.hilt.android, libs.plugins.hiltAndroid). Three sections make up the file: [versions] pins each version in one place, [libraries] maps an accessor name to a group:artifact, and [plugins] does the same for Gradle plugins.

            The win is that a version is never duplicated across modules: bump Retrofit from 2.11.0 to the next release by editing one line in [versions], and every module that uses it moves together.
        """.trimIndent(),
        code = """
            // gradle/libs.versions.toml (abbreviated)
            [versions]
            retrofit = "2.11.0"
            hiltVersion = "2.51.1"
            roomCommon = "2.6.1"

            [libraries]
            retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
            hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hiltVersion" }
            androidx-room-common = { group = "androidx.room", name = "room-common", version.ref = "roomCommon" }

            [plugins]
            hiltAndroid = { id = "com.google.dagger.hilt.android", version.ref = "hiltVersion" }

            // app/build.gradle.kts — consumed as generated accessors
            dependencies {
                implementation(libs.retrofit)
                implementation(libs.hilt.android)
                ksp(libs.hilt.compiler)
            }
        """.trimIndent()
    )
}

@Composable
fun BuildFlavors() {
    Section(
        title = "Build flavors (dev / staging / prod)",
        description = """
            Flavors let one codebase compile into several variants of the app that can install side by side. This app defines a single flavor dimension named "environment" with three flavors: dev, staging, and prod. dev and staging add an applicationIdSuffix and a versionNameSuffix so they install alongside the real app and are visibly different; prod ships the clean applicationId. Each flavor contributes its own source set under app/src/<flavor>/java, which is how the dev-only FailureInjectionInterceptor and FailingPostDao stay out of release builds entirely.

            The variant you build becomes part of the Gradle task name: ./gradlew assembleDevDebug produces the dev+debug APK, ./gradlew assembleProdRelease the prod+release one.
        """.trimIndent(),
        code = """
            // app/build.gradle.kts
            flavorDimensions += "environment"
            productFlavors {
                create("dev") {
                    dimension = "environment"
                    applicationIdSuffix = ".dev"
                    versionNameSuffix = "-dev"
                    buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
                }
                create("staging") {
                    dimension = "environment"
                    applicationIdSuffix = ".staging"
                    versionNameSuffix = "-staging"
                    buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
                }
                create("prod") {
                    dimension = "environment"
                    buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com/\"")
                }
            }
        """.trimIndent()
    )
}

@Composable
fun BuildConfig() {
    Section(
        title = "BuildConfig & flavor-specific values",
        description = """
            With buildConfig = true, Gradle generates a BuildConfig class from the buildConfigField entries. The advantage is that environment-specific values become ordinary Kotlin constants — type-checked, refactorable, and visible to the compiler — instead of being read from a properties file at runtime.

            This app uses that to give each flavor its own BASE_URL and its own content-provider authority. The Hilt module reads BuildConfig.BASE_URL when it constructs the Retrofit client, so swapping endpoints is purely a flavor decision.
        """.trimIndent(),
        code = """
            // generated, per flavor
            object BuildConfig {
                const val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
                const val contentProviderAuthority: String = "com.androiddevelopersguide.provider.dev"
            }

            // consumed in the flavor's DI module
            @Provides @Singleton
            fun provideRetrofit(client: OkHttpClient): Retrofit =
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        """.trimIndent()
    )
}

@Composable
fun KspVsKapt() {
    Section(
        title = "KSP vs kapt",
        description = """
            Annotation processors like Hilt's and Room's need a tool that runs them over your code during the build. kapt is the legacy choice: it stubs out Kotlin into Java first, which is slow. KSP (Kotlin Symbol Processing) is the modern replacement — it works directly on Kotlin symbols, so it is roughly twice as fast and supports newer Kotlin features.

            This app uses KSP for both Hilt and Room: ksp(libs.hilt.compiler) and ksp(libs.androidx.room.compiler). Reach for KSP first; only fall back to kapt when a library has no KSP support yet.
        """.trimIndent(),
        code = """
            // app/build.gradle.kts
            plugins {
                alias(libs.plugins.kotlinAndroidKsp)   // KSP, used for Hilt + Room
            }

            dependencies {
                implementation(libs.hilt.android)
                ksp(libs.hilt.compiler)                // KSP, not kapt

                implementation(libs.androidx.room.ktx)
                ksp(libs.androidx.room.compiler)       // KSP, not kapt
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun DependencyManagementScreenPreview() {
    DependencyManagementScreen(navController = rememberNavController())
}
