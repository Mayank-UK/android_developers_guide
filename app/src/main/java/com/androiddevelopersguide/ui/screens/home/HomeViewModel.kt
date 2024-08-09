package com.androiddevelopersguide.ui.screens.home

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Android fundamentals", routePath = NavRouter.AndroidFundamentalsScreen.route),
        LinkData(title = "Kotlin fundamentals", routePath = NavRouter.KotlinFundamentalsScreen.route),
        LinkData(title = "Jetpack compose", routePath = NavRouter.JetpackComposeScreen.route),
        LinkData(title = "XML layout", routePath = NavRouter.TempScreen.route),
        LinkData(title = "Other basics", routePath = NavRouter.OtherBasicsScreen.route),
        LinkData(title = "Lifecycle", routePath = NavRouter.LifecycleScreen.route),
        LinkData(title = "Dependency management", routePath = NavRouter.DependencyManagementScreen.route),
        LinkData(title = "API handling", routePath = NavRouter.ApiHandlingScreen.route),
        LinkData(title = "Database handling", routePath = NavRouter.DatabaseHandlingScreen.route),
        LinkData(title = "Architecture", routePath = NavRouter.ArchitectureScreen.route),
        LinkData(title = "Deployment", routePath = NavRouter.DeploymentScreen.route),
        LinkData(title = "Best practices", routePath = NavRouter.BestPracticesScreen.route),
    )
}