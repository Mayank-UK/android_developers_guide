package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.testing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun TestingScreen(
    navController: NavController,
    viewModel: TestingViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Testing",
        mobileUI = {
            ComposeUiTesting()
            UnitTesting()
            IntegrationTesting()
            SnapshotTesting()
            NavigationTesting()
        }
    )
}

@Composable
fun ComposeUiTesting() {
    Section(
        title = "Compose UI testing",
        description = """
            Compose UI testing allows you to test the user interface of your Compose components. The Compose testing library provides APIs for interacting with composables and verifying their behavior.
            
            Libraries:
            -AndroidX Test Compose: For Compose-specific UI testing.
        """.trimIndent(),
        code = """
            class MyComposableTest {

                @get:Rule
                val composeTestRule = createComposeRule()

                @Test
                fun testButtonClickUpdatesText() {
                    composeTestRule.setContent {
                        MyComposable()
                    }

                    // Check initial state
                    composeTestRule.onNodeWithText("Initial Text").assertExists()

                    // Perform click action
                    composeTestRule.onNodeWithText("Click Me").performClick()

                    // Verify updated state
                    composeTestRule.onNodeWithText("Updated Text").assertExists()
                }
            }
        """.trimIndent()
    )
}

@Composable
fun UnitTesting() {
    Section(
        title = "Unit testing",
        description = """
            Unit tests are used to test individual components or functions in isolation. For Jetpack Compose, this often involves testing ViewModels or other business logic.
            
            Libraries:
            -JUnit: 
            The standard framework for writing unit tests in Java and Kotlin.
            
            -MockK or Mockito: 
            For mocking dependencies.
        """.trimIndent(),
        code = """
            class MyViewModelTest {

                @Test
                fun testCalculateResult() {
                    val viewModel = MyViewModel()
                    viewModel.input.value = "10"
                    viewModel.calculate()
                    assertEquals(10, viewModel.result.value)
                }
            }
        """.trimIndent()
    )
}

@Composable
fun IntegrationTesting() {
    Section(
        title = "Integration testing",
        description = """
            Integration tests validate the interaction between different parts of your application, such as ViewModels and composables.
        """.trimIndent(),
        code = """
            class MyScreenTest {

                @get:Rule
                val composeTestRule = createComposeRule()

                @Test
                fun testViewModelInteraction() {
                    composeTestRule.setContent {
                        val viewModel: MyViewModel = viewModel()
                        MyScreen(viewModel = viewModel)
                    }

                    // Check initial UI state
                    composeTestRule.onNodeWithText("Initial State").assertExists()

                    // Perform action that changes the state
                    composeTestRule.onNodeWithText("Change State").performClick()

                    // Verify UI updates correctly
                    composeTestRule.onNodeWithText("New State").assertExists()
                }
            }
        """.trimIndent()
    )
}

@Composable
fun SnapshotTesting() {
    Section(
        title = "Snapshot testing",
        description = """
            Snapshot testing is used to capture the rendered output of composables and compare it against a stored snapshot.
            
            Add the snapshot testing library to your build.gradle:
            
            androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.0")
            androidTestImplementation("androidx.compose.ui:ui-test-manifest:1.4.0")

        """.trimIndent(),
        code = """
            class MyComposableSnapshotTest {

                @get:Rule
                val composeTestRule = createComposeRule()

                @Test
                fun testMyComposableSnapshot() {
                    composeTestRule.setContent {
                        MyComposable()
                    }

                    // Use the snapshot library to capture and compare the UI
                    composeTestRule.onRoot().captureToImage().assertAgainstGolden("my_composable_snapshot")
                }
            }
        """.trimIndent()
    )
}

@Composable
fun NavigationTesting() {
    Section(
        title = "Navigation testing",
        description = """
            For testing navigation, you often need to set up navigation in your tests and verify that the navigation actions work as expected.
        """.trimIndent(),
        code = """
            class NavigationTest {

                @get:Rule
                val composeTestRule = createComposeRule()

                @Test
                fun testNavigation() {
                    composeTestRule.setContent {
                        val navController = rememberNavController()
                        MyNavHost(navController = navController)
                    }

                    // Perform navigation action
                    composeTestRule.onNodeWithText("Navigate to Next").performClick()

                    // Verify the target screen is displayed
                    composeTestRule.onNodeWithText("Next Screen").assertExists()
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun TestingScreenPreview() {
    TestingScreen(navController = rememberNavController())
}