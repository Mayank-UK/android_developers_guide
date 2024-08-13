package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UiManipulationViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Layout", routePath = NavRouter.JetpackComposeUiManipulationLayoutScreen.route),
        LinkData(title = "Dimensions & constraints", routePath = NavRouter.JetpackComposeUiManipulationDimensionsAndConstraintsScreen.route),
        LinkData(title = "Spacing", routePath = NavRouter.JetpackComposeUiManipulationSpacingScreen.route),
        LinkData(title = "Colors & backgrounds", routePath = NavRouter.JetpackComposeUiManipulationColorsAndBackgroundsScreen.route),
        LinkData(title = "Alignment", routePath = NavRouter.JetpackComposeUiManipulationAlignmentScreen.route),
        LinkData(title = "Text", routePath = NavRouter.JetpackComposeUiManipulationTextScreen.route),
        LinkData(title = "Border", routePath = NavRouter.JetpackComposeUiManipulationBordersScreen.route),
        LinkData(title = "Icons & images", routePath = NavRouter.JetpackComposeUiManipulationIconsAndImagesScreen.route),
        LinkData(title = "Web & apps", routePath = NavRouter.JetpackComposeUiManipulationHyperlinkScreen.route),
        LinkData(title = "Form", routePath = NavRouter.JetpackComposeUiManipulationFormScreen.route),
        LinkData(title = "Effects", routePath = NavRouter.JetpackComposeUiManipulationEffectsScreen.route),
        LinkData(title = "Positioning", routePath = NavRouter.JetpackComposeUiManipulationPositioningScreen.route),
        LinkData(title = "Visibility", routePath = NavRouter.JetpackComposeUiManipulationVisibilityScreen.route),
        LinkData(title = "Interactive behaviours", routePath = NavRouter.JetpackComposeUiManipulationInteractiveBehavioursScreen.route),
        LinkData(title = "Media", routePath = NavRouter.JetpackComposeUiManipulationMediaScreen.route),
    )
}