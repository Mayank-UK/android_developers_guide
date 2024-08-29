package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.debugging

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun DebuggingScreen(
    navController: NavController,
    viewModel: DebuggingViewModel = hiltViewModel(),
) {
    PageScaffold(
        navController = navController,
        title = "Debugging",
        mobileUI = {
            AndroidStudioDebugger()
            Logcat()
            ComposePreview()
            StateInspection()
            InteractiveDebugging()
            PerformanceMonitoring()
        }
    )
}

@Composable
fun AndroidStudioDebugger() {
    Section(
        title = "Android studio debugger",
        description = """
            -Set Breakpoints: 
            Place breakpoints in your composables or ViewModels by clicking on the gutter next to the line number. When you run your app in debug mode, the debugger will pause execution at these breakpoints, allowing you to inspect the state and flow of your app.

            -Step Through Code: 
            Use the step over, step into, and step out buttons in the debugger to navigate through your code line-by-line to understand its behavior.

            -Inspect Variables: 
            While the debugger is paused, you can inspect the values of variables, view the call stack, and evaluate expressions.
        """.trimIndent(),
    )
}

@Composable
fun Logcat() {
    Section(
        title = "Logcat",
        description = """
            -Log Messages: 
            Use Log.d, Log.i, Log.w, or Log.e to print debug messages. This helps you trace the flow of your application and understand how different parts are executed.

            -Logging State Changes: 
            Log state changes in your composable or ViewModels to understand how your state is being updated.
        """.trimIndent(),
        code = """
            Log.d("DebugTag", "Debug message")

            Log.d("StateUpdate", "Current state: $/state")
        """.trimIndent()
    )
}

@Composable
fun ComposePreview() {
    Section(
        title = "Compose preview",
        description = """
            -Previews:
            Use the @Preview annotation to create composable previews. This allows you to render and debug your composables directly in Android Studio without running the app.
            
            -Preview Parameters: 
            Use @Preview parameters to test different states or configurations.
        """.trimIndent(),
        code = """
            @Preview(showBackground = true)
            @Composable
            fun MyPreview() {
                MyComposable()
            }

            @Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
            @Composable
            fun DarkModePreview() {
                MyComposable()
            }
        """.trimIndent()
    )
}

@Composable
fun StateInspection() {
    Section(
        title = "State inspection",
        description = """
            Use LaunchedEffect and SideEffect to log or debug state changes.
        """.trimIndent(),
        code = """
            LaunchedEffect(state) {
                Log.d("StateChange", "State changed: $/state")
            }
        """.trimIndent()
    )
}

@Composable
fun InteractiveDebugging() {
    Section(
        title = "Interactive debugging",
        description = """
            Use the Debug Inspector tool in Android Studio to inspect the hierarchy and state of composable at runtime. This tool is available under View > Tool Windows > Layout Inspector.
        """.trimIndent(),
    )
}

@Composable
fun PerformanceMonitoring() {
    Section(
        title = "Performance monitoring",
        description = """
            -Compose Metrics: 
            Use the Compose metrics and profiling tools in Android Studio to analyze the performance of your composable, such as recomposition counts and rendering time.

            -Profiler: 
            Use Android Studio’s profiler tools to monitor memory usage, CPU usage, and other performance metrics.
        """.trimIndent(),
    )
}

@Preview
@Composable
fun DebuggingScreenPreview() {
    DebuggingScreen(navController = rememberNavController())
}