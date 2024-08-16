package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.viewmodellifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ViewModelLifecycleScreen(
    navController: NavController,
    viewModel: ViewModelLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Viewmodel lifecycle",
            )
        },
        mobileUI = {
            Basics()
        }
    )
}

@Composable
fun Basics() {
    Section(
        title = "Basics",
        description = """
            Here's an overview of the ViewModel Lifecycle:

            1. Creation

            A ViewModel is created when the Activity or Fragment that owns it is created.
            The ViewModel is initialized with the necessary data and business logic.
            
            2. onCleared()

            Called when the ViewModel is no longer needed, such as when the Activity or Fragment is destroyed.
            The ViewModel should release any resources and clean up any ongoing tasks.
            
            3. Lifecycle Observing

            A ViewModel can observe the Lifecycle of its owner (Activity or Fragment) using the LifecycleObserver interface.
            The ViewModel can receive notifications when the owner's Lifecycle changes, such as when it's created, started, resumed, paused, stopped, or destroyed.
        """.trimIndent(),
        code = """
            public class MyViewModel extends ViewModel {
                private MutableLiveData<String> data = new MutableLiveData<>();

                public MyViewModel() {
                    // Initialize data and business logic
                }

                @Override
                protected void onCleared() {
                    super.onCleared();
                    // Release resources and clean up ongoing tasks
                }

                public LiveData<String> getData() {
                    return data;
                }

                public void loadData() {
                    // Load data from a repository or API
                    data.setValue("Loaded data!");
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ViewModelLifecycleScreenPreview() {
    ViewModelLifecycleScreen(navController = rememberNavController())
}