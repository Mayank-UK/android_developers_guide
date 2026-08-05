package com.androiddevelopersguide.ui.screens.androidfundamentals

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun AndroidFundamentalsScreen(
    navController: NavController,
    viewModel: AndroidFundamentalsViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Android fundamentals",
        mobileUI = {
            WhatIsAndroid()
            TheStack()
            ProjectSetup()
            AppAnatomy()
        }
    )
}

@Composable
fun WhatIsAndroid() {
    Section(
        title = "What is Android",
        description = """
            Android is a Linux-based, open-source operating system owned by Google, primarily targeting mobile devices but also tablets, TVs, watches, and cars. Apps are most often written in Kotlin (Google's preferred language) and run inside the Android Runtime (ART) on top of a Linux kernel.

            What makes Android distinctive as a development target: every app runs in its own sandboxed process under a unique Linux user id, components (activities, services, broadcast receivers, content providers) are activated by intents rather than direct constructor calls, and the system can destroy and recreate components at any time to reclaim memory. Designing for that possibility — state survives, the UI is disposable — is the single biggest mindset shift for newcomers.
        """.trimIndent(),
    )
}

@Composable
fun TheStack() {
    Section(
        title = "The platform stack",
        description = """
            From the bottom up: the Linux kernel handles drivers, power, and processes; ART (the Android Runtime) executes the dex bytecode your compiled Kotlin produces; the system services and framework (ActivityManager, WindowManager, PackageManager, etc.) expose the platform to apps over Binder IPC; and your app sits on top, calling those framework APIs through the AndroidX and Jetpack libraries.

            You rarely touch the lower layers directly. Your day-to-day surface is the Jetpack layer (Compose, ViewModel, Room, Navigation, Hilt) sitting on the framework layer.
        """.trimIndent(),
    )
}

@Composable
fun ProjectSetup() {
    Section(
        title = "Project setup",
        description = """
            Install Android Studio, which bundles the Android SDK, an emulator, and the Gradle build tooling. When you create a new project you pick a template (Empty Activity for a Compose app is the modern default), a minimum SDK (the oldest Android version you will support — this app targets SDK 34 and supports back to 24), and a package name that becomes your applicationId.

            The project that opens is a Gradle module with the layout this app follows: app/src/main/java for code, app/src/main/res for resources (strings, drawables, themes), and app/build.gradle.kts for build configuration including flavors and dependencies.
        """.trimIndent(),
    )
}

@Composable
fun AppAnatomy() {
    Section(
        title = "Anatomy of an Android app",
        description = """
            An Android app is assembled from four kinds of components, declared in the AndroidManifest.xml:

            -Activity: a single screen with a UI. The entry point for most user interactions.
            -Service: background work without a UI (music playback, file sync). Prefer WorkManager for deferrable background work.
            -Broadcast receiver: reacts to system-wide announcements (connectivity changes, boot completed).
            -Content provider: exposes app data to other apps through a cursor-style API.

            This app uses several of them directly: MainActivity hosts the Compose UI, a NetworkConnectivityReceiver listens for connectivity changes, and a UserProvider exposes data through a content-provider authority. Components are activated by intents, launched by the system, and have lifecycles you must respect — which is why state belongs in a ViewModel, not in the component itself.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun AndroidFundamentalsScreenPreview() {
    AndroidFundamentalsScreen(navController = rememberNavController())
}
