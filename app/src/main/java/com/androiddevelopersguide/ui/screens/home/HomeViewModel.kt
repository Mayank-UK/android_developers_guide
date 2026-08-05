package com.androiddevelopersguide.ui.screens.home

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.domain.models.LinkIcon
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    /**
     * Home entries grouped by category, each carrying a one-line description and icon key.
 * The single source of truth for the home grid; the XML-layout entry is represented
 * as a [LinkData] too and is rendered alongside the others (its navigation is handled
 * specially in `HomeScreen` via a route sentinel).
 */
    val groupedLinks: List<LinkCategory> = listOf(
        LinkCategory(
            title = "Fundamentals",
            links = listOf(
                LinkData(
                    title = "Android fundamentals",
                    description = "App components, the manifest, intents and the platform model.",
                    icon = LinkIcon.ANDROID,
                    category = "Fundamentals",
                    routePath = NavRouter.AndroidFundamentalsScreen.route
                ),
                LinkData(
                    title = "Kotlin fundamentals",
                    description = "Syntax, nullability, coroutines and idiomatic Kotlin.",
                    icon = LinkIcon.KOTLIN,
                    category = "Fundamentals",
                    routePath = NavRouter.KotlinFundamentalsScreen.route
                ),
                LinkData(
                    title = "Other basics",
                    description = "Activity, Intent, Service, BroadcastReceiver, WorkManager and more.",
                    icon = LinkIcon.BASICS,
                    category = "Fundamentals",
                    routePath = NavRouter.OtherBasicsScreen.route
                ),
                LinkData(
                    title = "Lifecycle",
                    description = "Activity, fragment, service, view-model and compose lifecycles.",
                    icon = LinkIcon.LIFECYCLE,
                    category = "Fundamentals",
                    routePath = NavRouter.LifecycleScreen.route
                )
            )
        ),
        LinkCategory(
            title = "UI",
            links = listOf(
                LinkData(
                    title = "Jetpack compose",
                    description = "Declarative UI: layout, state, navigation, effects and catalog.",
                    icon = LinkIcon.COMPOSE,
                    category = "UI",
                    routePath = NavRouter.JetpackComposeScreen.route
                ),
                LinkData(
                    title = "Widgets",
                    description = "App widgets, remote views and the home-screen surface.",
                    icon = LinkIcon.WIDGETS,
                    category = "UI",
                    routePath = NavRouter.WidgetsScreen.route
                ),
                LinkData(
                    title = "XML layout",
                    description = "Classic View-system layout alongside Compose (opens a new activity).",
                    icon = LinkIcon.XML,
                    category = "UI",
                    routePath = XML_LAYOUT_ROUTE
                )
            )
        ),
        LinkCategory(
            title = "Architecture & data",
            links = listOf(
                LinkData(
                    title = "Architecture",
                    description = "Clean layered MVVM: domain, data and UI boundaries.",
                    icon = LinkIcon.ARCHITECTURE,
                    category = "Architecture & data",
                    routePath = NavRouter.ArchitectureScreen.route
                ),
                LinkData(
                    title = "Dependency management",
                    description = "Version catalogs, Hilt modules and DI scoping.",
                    icon = LinkIcon.DEPENDENCY,
                    category = "Architecture & data",
                    routePath = NavRouter.DependencyManagementScreen.route
                ),
                LinkData(
                    title = "API handling",
                    description = "Retrofit + OkHttp + Gson networking with Result<T>.",
                    icon = LinkIcon.API,
                    category = "Architecture & data",
                    routePath = NavRouter.ApiHandlingScreen.route
                ),
                LinkData(
                    title = "Database handling",
                    description = "Room DAOs, entities and local persistence.",
                    icon = LinkIcon.DATABASE,
                    category = "Architecture & data",
                    routePath = NavRouter.DatabaseHandlingScreen.route
                )
            )
        ),
        LinkCategory(
            title = "Platform & tooling",
            links = listOf(
                LinkData(
                    title = "Deployment",
                    description = "Flavors, build types, signing and release packaging.",
                    icon = LinkIcon.DEPLOYMENT,
                    category = "Platform & tooling",
                    routePath = NavRouter.DeploymentScreen.route
                ),
                LinkData(
                    title = "Best practices",
                    description = "Patterns, conventions and quality guidelines used throughout.",
                    icon = LinkIcon.BEST_PRACTICES,
                    category = "Platform & tooling",
                    routePath = NavRouter.BestPracticesScreen.route
                )
            )
        )
    )

    companion object {
        /** Sentinel [LinkData.routePath] marking the XML-layout entry (launched via startActivity). */
        const val XML_LAYOUT_ROUTE = "__xml_layout_activity__"
    }
}

/** A titled group of [LinkData] entries rendered as a section on the home grid. */
data class LinkCategory(
    val title: String,
    val links: List<LinkData>
)
