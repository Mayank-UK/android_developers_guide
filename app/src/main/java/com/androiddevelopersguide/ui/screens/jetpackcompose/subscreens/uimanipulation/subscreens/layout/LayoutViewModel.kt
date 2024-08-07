package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Layout elements", routePath = NavRouter.JetpackComposeUiManipulationLayoutElementsScreen.route),
        LinkData(title = "Screen structure", routePath = NavRouter.JetpackComposeUiManipulationScreenStructureScreen.route),
    )
}