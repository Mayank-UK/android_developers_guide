package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.lifecycle

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun JetpackLifecycleScreen(
    navController: NavController,
    viewModel: JetpackLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Lifecycle",
            )
        },
        mobileUI = {
            Basics()
            LaunchEffectFun()
            DisposableEffectFun()
            SideEffectFun()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            In Jetpack Compose, there are no traditional lifecycle methods like onCreate, onStart, onResume, onPause, onStop, and onDestroy as in Android's Activity or Fragment lifecycle. 
            
            Instead, Compose provides a set of APIs to handle the lifecycle of a composable function.
        """.trimIndent(),
    )
}

@Composable
fun LaunchEffectFun() {
    Section(
        title = "LaunchEffect",
        description = """
            This is a composable function that launches a coroutine when the composition is initialized or when the keys change. It's similar to onCreate or onStart in the traditional Android lifecycle.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                Toast.makeText(context, "LaunchEffect called", Toast.LENGTH_SHORT).show()
            }
        },
        code = """
            val context = LocalContext.current

            LaunchedEffect(Unit) {
                Toast.makeText(context, "LaunchEffect called", Toast.LENGTH_SHORT).show()
            }            
        """.trimIndent()
    )
}

@Composable
fun DisposableEffectFun() {
    Section(
        title = "DisposableEffect",
        description = """
            This is a composable function that provides a way to dispose of resources when the composition is no longer needed. It's similar to onDestroy in the traditional Android lifecycle.
            
            It is also called when a composition is initialized, and all the code outside onDispose is executed, and when a composition is disposed than it's onDispose is called. 
        """.trimIndent(),
        demo = {
            val context = LocalContext.current

            DisposableEffect(Unit) {
                Toast.makeText(context, "DisposableEffect called during init", Toast.LENGTH_SHORT).show()

                onDispose {
                    Toast.makeText(context, "DisposableEffect called", Toast.LENGTH_SHORT).show()
                }

            }
        },
        code = """
            val context = LocalContext.current

            DisposableEffect(Unit) {
                Toast.makeText(context, "DisposableEffect called during init", Toast.LENGTH_SHORT).show()

                onDispose {
                    Toast.makeText(context, "DisposableEffect called", Toast.LENGTH_SHORT).show()
                }
            }
        """.trimIndent()
    )
}

@Composable
fun SideEffectFun() {
    Section(
        title = "SideEffect",
        description = """
            This is a composable function that performs a side effect when the composition is initialized or when the keys change. It's similar to onResume in the traditional Android lifecycle.
        """.trimIndent(),
        demo = {
            val context = LocalContext.current
            var count by remember { mutableStateOf(0) }

            SideEffect {
                Toast.makeText(context, "SideEffect called", Toast.LENGTH_SHORT).show()
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { count++ }) {
                    Text("Update variable")
                }
                Spacer(Modifier.width(16.dp))
                Text("$count")
            }
        },
        code = """
            val context = LocalContext.current
            var count by remember { mutableStateOf(0) }

            SideEffect {
                Toast.makeText(context, "SideEffect called", Toast.LENGTH_SHORT).show()
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { count++ }) {
                    Text("Update variable")
                }
                Spacer(Modifier.width(16.dp))
                Text("$\count")
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun JetpackLifecycleScreenPreview() {
    JetpackLifecycleScreen(navController = rememberNavController())
}