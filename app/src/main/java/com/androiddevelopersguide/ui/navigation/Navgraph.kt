package com.androiddevelopersguide.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.androiddevelopersguide.ui.screens.androidfundamentals.AndroidFundamentalsScreen
import com.androiddevelopersguide.ui.screens.apihandling.ApiHandlingScreen
import com.androiddevelopersguide.ui.screens.apihandling.subscreens.postdetail.PostDetailScreen
import com.androiddevelopersguide.ui.screens.apihandling.subscreens.posts.PostsScreen
import com.androiddevelopersguide.ui.screens.architecture.ArchitectureScreen
import com.androiddevelopersguide.ui.screens.bestpractices.BestPracticesScreen
import com.androiddevelopersguide.ui.screens.databasehandling.DatabaseHandlingScreen
import com.androiddevelopersguide.ui.screens.dependencymanagement.DependencyManagementScreen
import com.androiddevelopersguide.ui.screens.deployment.DeploymentScreen
import com.androiddevelopersguide.ui.screens.deployment.subscreens.proddeployment.ProdDeploymentScreen
import com.androiddevelopersguide.ui.screens.deployment.subscreens.testdeployment.TestDeploymentScreen
import com.androiddevelopersguide.ui.screens.home.HomeScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.JetpackComposeScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.debugging.DebuggingScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.lifecycle.JetpackLifecycleScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.nativefeatures.NativeFeaturesScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.navigation.NavigationScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.responsivedesign.ResponsiveDesignScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.statemanagement.StateManagementScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.supportingclassesandenums.SupportingClassesAndEnumsScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.testing.TestingScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uielementscatalog.UiElementsCatalogScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.UiManipulationScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.alignment.AlignmentScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.borders.BordersScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.colorsandbackgrounds.ColorsAndBackgroundsScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.dimensionsandconstraints.DimensionsAndConstraintsScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.effects.EffectsScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.form.FormScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.hyperlink.HyperlinkScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.iconsandimages.IconsAndImagesScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.interactivebehaviours.InteractiveBehavioursScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout.LayoutScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout.subscreens.layoutelements.LayoutElementsScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.layout.subscreens.screenstructure.ScreenStructureScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.media.MediaScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.positioning.PositioningScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.spacing.SpacingScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.text.TextScreen
import com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uimanipulation.subscreens.visibility.VisibilityScreen
import com.androiddevelopersguide.ui.screens.kotlinfundamentals.KotlinFundamentalsScreen
import com.androiddevelopersguide.ui.screens.lifecycle.LifecycleScreen
import com.androiddevelopersguide.ui.screens.lifecycle.subscreens.activitylifecycle.ActivityLifecycleScreen
import com.androiddevelopersguide.ui.screens.lifecycle.subscreens.broadcastreceiverlifecycle.BroadcastReceiverScreen
import com.androiddevelopersguide.ui.screens.lifecycle.subscreens.fragmentlifecycle.FragmentLifecycleScreen
import com.androiddevelopersguide.ui.screens.lifecycle.subscreens.jetpackcomposelifecycle.JetpackComposeLifecycleReferScreen
import com.androiddevelopersguide.ui.screens.lifecycle.subscreens.servicelifecycle.ServiceLifecycleScreen
import com.androiddevelopersguide.ui.screens.lifecycle.subscreens.viewmodellifecycle.ViewModelLifecycleScreen
import com.androiddevelopersguide.ui.temp.TempScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = NavRouter.HomeScreen.route, modifier) {
        composable(NavRouter.TempScreen.route) { TempScreen(navController = navController) }

        composable(NavRouter.HomeScreen.route) { HomeScreen(navController = navController) }

        // android fundamentals
        composable(NavRouter.AndroidFundamentalsScreen.route) { AndroidFundamentalsScreen(navController = navController) }

        // kotlin fundamentals
        composable(NavRouter.KotlinFundamentalsScreen.route) { KotlinFundamentalsScreen(navController = navController) }

        // jetpack compose
        composable(NavRouter.JetpackComposeScreen.route) { JetpackComposeScreen(navController = navController) }
            composable(NavRouter.JetpackComposeUiManipulationScreen.route) { UiManipulationScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationLayoutScreen.route) { LayoutScreen(navController = navController) }
                    composable(NavRouter.JetpackComposeUiManipulationLayoutElementsScreen.route) { LayoutElementsScreen(navController = navController) }
                    composable(NavRouter.JetpackComposeUiManipulationScreenStructureScreen.route) { ScreenStructureScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationDimensionsAndConstraintsScreen.route) { DimensionsAndConstraintsScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationSpacingScreen.route) { SpacingScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationColorsAndBackgroundsScreen.route) { ColorsAndBackgroundsScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationAlignmentScreen.route) { AlignmentScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationTextScreen.route) { TextScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationBordersScreen.route) { BordersScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationIconsAndImagesScreen.route) { IconsAndImagesScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationHyperlinkScreen.route) { HyperlinkScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationFormScreen.route) { FormScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationEffectsScreen.route) { EffectsScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationPositioningScreen.route) { PositioningScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationVisibilityScreen.route) { VisibilityScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationInteractiveBehavioursScreen.route) { InteractiveBehavioursScreen(navController = navController) }
                composable(NavRouter.JetpackComposeUiManipulationMediaScreen.route) { MediaScreen(navController = navController) }
            composable(NavRouter.JetpackComposeLifecycleScreen.route) { JetpackLifecycleScreen(navController = navController) }
            composable(NavRouter.JetpackComposeNavigationScreen.route) { NavigationScreen(navController = navController) }
            composable(NavRouter.JetpackComposeStateManagementScreen.route) { StateManagementScreen(navController = navController) }
            composable(NavRouter.JetpackComposeUiElementsCatalogScreen.route) { UiElementsCatalogScreen(navController = navController) }
            composable(NavRouter.JetpackComposeSupportingClassesAndEnumsScreen.route) { SupportingClassesAndEnumsScreen(navController = navController) }
            composable(NavRouter.JetpackComposeNativeFeaturesScreen.route) { NativeFeaturesScreen(navController = navController) }
            composable(NavRouter.JetpackComposeResponsiveDesignScreen.route) { ResponsiveDesignScreen(navController = navController) }
            composable(NavRouter.JetpackComposeDebuggingScreen.route) { DebuggingScreen(navController = navController) }
            composable(NavRouter.JetpackComposeTestingScreen.route) { TestingScreen(navController = navController) }

        // xml layout
//        composable(NavRouter.XmlLayoutScreen.route) { XmlLayoutScreen(navController = navController) }

        // lifecycle
        composable(NavRouter.LifecycleScreen.route) { LifecycleScreen(navController = navController) }
            composable(NavRouter.ActivityLifecycleScreen.route) { ActivityLifecycleScreen(navController = navController) }
            composable(NavRouter.BroadcastReceiverScreen.route) { BroadcastReceiverScreen(navController = navController) }
            composable(NavRouter.FragmentLifecycleScreen.route) { FragmentLifecycleScreen(navController = navController) }
            composable(NavRouter.JetpackComposeLifecycleReferScreen.route) { JetpackComposeLifecycleReferScreen(navController = navController) }
            composable(NavRouter.ServiceLifecycleScreen.route) { ServiceLifecycleScreen(navController = navController) }
            composable(NavRouter.ViewModelLifecycleScreen.route) { ViewModelLifecycleScreen(navController = navController) }

        // dependency and management
        composable(NavRouter.DependencyManagementScreen.route) { DependencyManagementScreen(navController = navController) }

        // api handling
        composable(NavRouter.ApiHandlingScreen.route) { ApiHandlingScreen(navController = navController) }
            composable(NavRouter.PostsScreen.route) {
                PostsScreen(onUserClick = { postId ->
                    navController.navigate("${NavRouter.PostDetailScreen.route}/$postId")
                })
            }
            composable(
                NavRouter.PostDetailScreen.route + NavRouter.PostDetailScreen.objectPath,
                arguments = listOf(navArgument(NavRouter.PostDetailScreen.objectName) { type = NavType.IntType })
            ) { backStackEntry ->
                val postId = backStackEntry.arguments?.getInt(NavRouter.PostDetailScreen.objectName) ?: return@composable
                PostDetailScreen(postId = postId)
            }

        // database handling
        composable(NavRouter.DatabaseHandlingScreen.route) { DatabaseHandlingScreen(navController = navController) }

        // architecture
        composable(NavRouter.ArchitectureScreen.route) { ArchitectureScreen(navController = navController) }

        // deployment
        composable(NavRouter.DeploymentScreen.route) { DeploymentScreen(navController = navController) }
            composable(NavRouter.TestDeploymentScreen.route) { TestDeploymentScreen(navController = navController) }
            composable(NavRouter.ProdDeploymentScreen.route) { ProdDeploymentScreen(navController = navController) }

        // best practices
        composable(NavRouter.BestPracticesScreen.route) { BestPracticesScreen(navController = navController) }
    }
}