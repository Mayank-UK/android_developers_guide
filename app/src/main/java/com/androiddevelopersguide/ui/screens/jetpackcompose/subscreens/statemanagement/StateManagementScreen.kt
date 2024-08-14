package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.statemanagement

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun StateManagementScreen(
    navController: NavController,
    viewModel: StateMangementViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "State management",
            )
        },
        mobileUI = {
            Basics()
            MutableStateOf()
            ViewModelAndLiveData()
            StateFlow()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            Although there are multiple ways to handle state in the compose application, but the most used are "mutableStateOf" and viewModels with hilt dependencies as used in this application. 
        """.trimIndent(),
    )
}


@Composable
fun MutableStateOf() {
    Section(
        title = "MutableStateOf",
        description = """
            "mutableStateOf" is a function that creates a mutable state object, which can be used to store and update state in a composable function. remember is a function that caches the result of a computation, so that it's not recomputed on every recomposition.
        """.trimIndent(),
        demo = {
            val count = remember { mutableStateOf(0) }

            Button(onClick = { count.value++ }) {
                Text("Count: ${count.value}")
            }
        },
        code = """
            val count = remember { mutableStateOf(0) }

            Button(onClick = { count.value++ }) {
                Text("Count: $\{count.value}")
            }
        """.trimIndent()
    )
}

@Composable
fun ViewModelAndLiveData() {
    Section(
        title = "ViewModel & LiveData",
        description = """
            ViewModel is a class that holds and manages UI-related data, and LiveData is a lifecycle-aware data holder that notifies observers when the data changes.
            
            Developers typically use hilt dependency injection to inject viewmodel in views. 
        """.trimIndent(),
        code = """
            class CounterViewModel : ViewModel() {
                private val _count = MutableLiveData(0)
                val count: LiveData<Int> = _count

                fun incrementCount() {
                    viewModelScope.launch {
                        _count.value = _count.value!! + 1
                    }
                }
            }

            @Composable
            fun Counter(viewModel: CounterViewModel = viewModel()) {
                val count by viewModel.count.observeAsState(0)

                Button(onClick = { viewModel.incrementCount() }) {
                    Text("Count: $/count")
                }
            }
        """.trimIndent()
    )
}

@Composable
fun StateFlow() {
    Section(
        title = "StateFlow",
        description = """
            StateFlow is a flow that emits the current state value and any subsequent state updates, and LifecycleScope is a scope that's tied to the lifecycle of a composable function.
        """.trimIndent(),
        code = """
            class CounterViewModel {
                private val _count = MutableStateFlow(0)
                val count: StateFlow<Int> = _count.stateIn(lifecycleScope, initialValue = 0)

                fun incrementCount() {
                    lifecycleScope.launch {
                        _count.value = _count.value + 1
                    }
                }
            }

            @Composable
            fun Counter(viewModel: CounterViewModel = viewModel()) {
                val count by viewModel.count.collectAsState(0)

                Button(onClick = { viewModel.incrementCount() }) {
                    Text("Count: $/count")
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun StateManagementScreenPreview() {
    StateManagementScreen(navController = rememberNavController())
}