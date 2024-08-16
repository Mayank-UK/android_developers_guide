package com.androiddevelopersguide.ui.navigation

sealed class NavRouter(
    val route: String,
    val objectName: String = "",
    val objectPath: String = ""
) {
    object TempScreen : NavRouter("temp")

    object HomeScreen : NavRouter("home")

    // android fundamental
    object AndroidFundamentalsScreen : NavRouter("android_fundamentals")

    // kotlin fundamental
    object KotlinFundamentalsScreen : NavRouter("kotlin_fundamentals")

    // jetpack compose
    object JetpackComposeScreen : NavRouter("jetpack_compose")
        object JetpackComposeUiManipulationScreen : NavRouter("jetpack_compose_ui_manipulation")
            object JetpackComposeUiManipulationLayoutScreen : NavRouter("jetpack_compose_ui_manipulation_layout")
                object JetpackComposeUiManipulationLayoutElementsScreen : NavRouter("jetpack_compose_ui_manipulation_layout_elements")
                object JetpackComposeUiManipulationScreenStructureScreen : NavRouter("jetpack_compose_ui_manipulation_screen_structure")
            object JetpackComposeUiManipulationDimensionsAndConstraintsScreen : NavRouter("jetpack_compose_ui_manipulation_dimensions_and_constraints")
            object JetpackComposeUiManipulationSpacingScreen : NavRouter("jetpack_compose_ui_manipulation_spacing")
            object JetpackComposeUiManipulationColorsAndBackgroundsScreen : NavRouter("jetpack_compose_ui_manipulation_colors_and_backgrounds")
            object JetpackComposeUiManipulationAlignmentScreen : NavRouter("jetpack_compose_ui_manipulation_alignment")
            object JetpackComposeUiManipulationTextScreen : NavRouter("jetpack_compose_ui_manipulation_text")
            object JetpackComposeUiManipulationBordersScreen : NavRouter("jetpack_compose_ui_manipulation_borders")
            object JetpackComposeUiManipulationIconsAndImagesScreen : NavRouter("jetpack_compose_ui_manipulation_icons_and_images")
            object JetpackComposeUiManipulationHyperlinkScreen : NavRouter("jetpack_compose_ui_manipulation_hyperlink")
            object JetpackComposeUiManipulationFormScreen : NavRouter("jetpack_compose_ui_manipulation_form")
            object JetpackComposeUiManipulationEffectsScreen : NavRouter("jetpack_compose_ui_manipulation_effects")
            object JetpackComposeUiManipulationPositioningScreen : NavRouter("jetpack_compose_ui_manipulation_positioning")
            object JetpackComposeUiManipulationVisibilityScreen : NavRouter("jetpack_compose_ui_manipulation_visibility")
            object JetpackComposeUiManipulationInteractiveBehavioursScreen : NavRouter("jetpack_compose_ui_manipulation_interactive_behaviours")
            object JetpackComposeUiManipulationMediaScreen : NavRouter("jetpack_compose_ui_manipulation_media")
        object JetpackComposeLifecycleScreen : NavRouter("jetpack_compose_lifecycle")
        object JetpackComposeNavigationScreen : NavRouter("jetpack_compose_navigation")
        object JetpackComposeStateManagementScreen : NavRouter("jetpack_compose_state_management")
        object JetpackComposeUiElementsCatalogScreen : NavRouter("jetpack_compose_ui_elements_catalog")
        object JetpackComposeSupportingClassesAndEnumsScreen : NavRouter("jetpack_compose_supporting_classes_and_enums")
        object JetpackComposeDesignSystemScreen : NavRouter("jetpack_compose_design_system")
        object JetpackComposeNativeFeaturesScreen : NavRouter("jetpack_compose_native_features")
        object JetpackComposeResponsiveDesignScreen : NavRouter("jetpack_compose_responsive_design")
        object JetpackComposeDebuggingScreen : NavRouter("jetpack_compose_debugging")
        object JetpackComposeTestingScreen : NavRouter("jetpack_compose_testing")

    // other basics
    object OtherBasicsScreen : NavRouter("other_basics")
        object ActivityScreen : NavRouter("activity")
        object IntentScreen : NavRouter("intent")
        object ServiceScreen : NavRouter("service")
        object BroadcastReceiverScreen : NavRouter("broadcast_receiver")
        object ContentProviderScreen : NavRouter("content_provider")

    // lifecycle
    object LifecycleScreen : NavRouter("lifecycle")
        object ActivityLifecycleScreen : NavRouter("activity_lifecycle")
        object BroadcastReceiverLifecycleScreen : NavRouter("broadcast_receiver_lifecycle")
        object FragmentLifecycleScreen : NavRouter("fragment_lifecycle")
        object ServiceLifecycleScreen : NavRouter("service_lifecycle")
        object ViewModelLifecycleScreen : NavRouter("view_model_lifecycle")

    // dependency and management
    object DependencyManagementScreen : NavRouter("dependency_management")

    // api handling
    object ApiHandlingScreen : NavRouter("api_handling")
        object PostsScreen : NavRouter("posts")
        object PostDetailScreen : NavRouter("post_detail", objectName = "postId", objectPath = "/{postId}")

    // database handling
    object DatabaseHandlingScreen : NavRouter("database_handling")

    // architecture
    object ArchitectureScreen : NavRouter("architecture")

    // deployment
    object DeploymentScreen : NavRouter("deployment")

    // best practices
    object BestPracticesScreen : NavRouter("best_practices")
}