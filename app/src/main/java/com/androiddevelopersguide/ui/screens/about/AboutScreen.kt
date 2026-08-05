package com.androiddevelopersguide.ui.screens.about

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section
import com.androiddevelopersguide.ui.theme.Sizes

/**
 * Mirrors the React guide's About page, scoped to the facts of this Android app. All counts and
 * stack entries below are read from the codebase (nav routes + `gradle/libs.versions.toml`) so
 * they stay accurate as the project evolves.
 */
@Composable
fun AboutScreen(navController: NavController) {
    PageScaffold(
        navController = navController,
        title = "About",
        mobileUI = {
            Spacer(Modifier.height(Sizes.sm))

            Text(
                text = "Android Developer's Guide",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(Sizes.sm))

            Section(
                title = "What this app is",
                description = "A hands-on reference and learning app for modern Android " +
                    "development, written in Kotlin with Jetpack Compose. It is the Android " +
                    "sibling of the Flutter and React developer guides — each screen is a small, " +
                    "runnable demonstration of a concept rather than a static explanation."
            )

            Section(
                title = "What it covers",
                description = "The app is organised into topic sections reachable from the home " +
                    "screen: Android fundamentals, Kotlin fundamentals, Jetpack Compose (UI " +
                    "manipulation, lifecycle, navigation, state management, UI elements catalog, " +
                    "design system, native features, responsive design, debugging, testing and " +
                    "more), Other basics (Activity, Intent, Service, BroadcastReceiver, " +
                    "WorkManager, Notification, ContentProvider), Lifecycle, Widgets, Dependency " +
                    "management, API handling, Database handling, Architecture, Deployment and " +
                    "Best practices."
            )

            Section(
                title = "The stack",
                description = "Kotlin, Jetpack Compose, Material 3, Hilt (dependency injection), " +
                    "Retrofit + OkHttp + Gson (networking), Room (local persistence), Kotlin " +
                    "Coroutines (async), WorkManager (background work), Navigation-Compose, " +
                    "CameraX, and DataStore Preferences (for the persisted theme preference)."
            )

            Section(
                title = "How it's built",
                description = "Clean layered MVVM: a `domain` layer (models, repository " +
                    "interfaces, use cases), a `data` layer (repository implementations, DTOs, " +
                    "Room DAOs, remote ApiService, mappers), a `core` layer (Result<T>, " +
                    "severity-tagged Failures — INFO / WARNING / CRITICAL — exception types and " +
                    "guarded repository/api/database call helpers), and a `ui` layer (screens, " +
                    "view models and the shared design system). Hilt wires everything together " +
                    "from modules under `di/`."
            )

            Section(
                title = "Conventions it follows",
                description = "Screens compose the shared `PageScaffold` + `Section` + design " +
                    "tokens so the chrome, spacing and typography stay consistent. Cross-cutting " +
                    "chrome (drawer, theme toggle) is provided through CompositionLocals. State " +
                    "flows down from ViewModels; `Result<T>` carries both data and structured " +
                    "failures. Three build flavors — dev, staging, prod — share the same Room " +
                    "database while swapping network/data behaviour."
            )
        }
    )
}
