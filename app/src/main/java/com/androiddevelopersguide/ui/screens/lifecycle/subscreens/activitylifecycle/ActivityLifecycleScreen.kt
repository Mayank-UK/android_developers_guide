package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.activitylifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun ActivityLifecycleScreen(
    navController: NavController,
    viewModel: ActivityLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Activity lifecycle",
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
            Here are the stages of the Android Activity Lifecycle:

            1. onCreate()

            Called when the Activity is first created.
            Initialize the Activity's UI, load data, and set up the layout.
            This is the first method called in the Activity's lifecycle.
            
            2. onStart()

            Called when the Activity becomes visible to the user.
            The Activity is now visible, but it may not have focus yet.
            This method is called after onCreate() and before onResume().
            
            3. onResume()

            Called when the Activity gains focus and becomes interactive.
            The Activity is now in the foreground and the user can interact with it.
            This method is called after onStart().
            
            4. onPause()

            Called when the Activity loses focus, but is still visible.
            The Activity is no longer in the foreground, but it's still visible.
            This method is called when the user navigates away from the Activity or when another Activity takes focus.
            
            5. onStop()

            Called when the Activity is no longer visible to the user.
            The Activity is no longer visible, and the system may release resources.
            This method is called after onPause().

            6. onDestroy()

            Called when the Activity is about to be destroyed.
            Release all resources, close database connections, and perform any necessary cleanup.
            This is the last method called in the Activity's lifecycle.

            7. onRestart()

            Called when the Activity is being restarted after being stopped.
            This method is called after onStop() and before onStart().
        """.trimIndent(),
        code = """
            public class LifecycleActivity extends Activity {
                private static final String TAG = "LifecycleActivity";

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    Log.d(TAG, "onCreate()");
                    setContentView(R.layout.activity_lifecycle);
                }

                @Override
                protected void onStart() {
                    super.onStart();
                    Log.d(TAG, "onStart()");
                }

                @Override
                protected void onResume() {
                    super.onResume();
                    Log.d(TAG, "onResume()");
                }

                @Override
                protected void onPause() {
                    super.onPause();
                    Log.d(TAG, "onPause()");
                }

                @Override
                protected void onStop() {
                    super.onStop();
                    Log.d(TAG, "onStop()");
                }

                @Override
                protected void onDestroy() {
                    super.onDestroy();
                    Log.d(TAG, "onDestroy()");
                }

                @Override
                protected void onRestart() {
                    super.onRestart();
                    Log.d(TAG, "onRestart()");
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun ActivityLifecycleScreenPreview() {
    ActivityLifecycleScreen(navController = rememberNavController())
}