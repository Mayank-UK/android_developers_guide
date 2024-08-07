package com.androiddevelopersguide.ui.screens.jetpackcompose

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JetpackComposeViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "UI manipulation", routePath = NavRouter.JetpackComposeUiManipulationScreen.route),
        LinkData(title = "Lifecycle", routePath = NavRouter.JetpackComposeLifecycleScreen.route),
        LinkData(title = "Navigation", routePath = NavRouter.JetpackComposeNavigationScreen.route),
        LinkData(title = "State management", routePath = NavRouter.JetpackComposeStateManagementScreen.route),
        LinkData(title = "UI elements catalog", routePath = NavRouter.JetpackComposeUiElementsCatalogScreen.route),
        LinkData(title = "Supporting classes & enums", routePath = NavRouter.JetpackComposeSupportingClassesAndEnumsScreen.route),
        LinkData(title = "Native features", routePath = NavRouter.JetpackComposeNativeFeaturesScreen.route),
        LinkData(title = "Responsive design", routePath = NavRouter.JetpackComposeResponsiveDesignScreen.route),
        LinkData(title = "Debugging", routePath = NavRouter.JetpackComposeDebuggingScreen.route),
        LinkData(title = "Testing", routePath = NavRouter.JetpackComposeTestingScreen.route),
    )
}