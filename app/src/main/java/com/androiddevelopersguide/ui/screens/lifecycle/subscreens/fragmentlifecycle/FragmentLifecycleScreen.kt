package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.fragmentlifecycle

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.androiddevelopersguide.ui.components.AppBar
import com.androiddevelopersguide.ui.components.PageScaffold
import com.androiddevelopersguide.ui.components.Section

@Composable
fun FragmentLifecycleScreen(
    navController: NavController,
    viewModel: FragmentLifecycleViewModel = hiltViewModel(),
) {
    PageScaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = "Fragment lifecycle",
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
            Fragments have their own lifecycle, which is closely tied to the lifecycle of the Activity they are attached to.
            Here's an overview of the Fragment Lifecycle:

            1. onAttach(Context)

            Called when the Fragment is attached to the Activity.
            The Fragment is not yet initialized, and the Activity is not yet fully created.
            
            2. onCreate(Bundle)

            Called when the Fragment is created.
            The Fragment is initialized, but the Activity is not yet fully created.
            This is where you can initialize the Fragment's variables and load data.
            
            3. onCreateView(LayoutInflater, ViewGroup, Bundle)

            Called when the Fragment's view is created.
            The Fragment's view is inflated, and you can set up your UI components.
            
            4. onViewCreated(View, Bundle)

            Called when the Fragment's view is fully created.
            You can initialize your UI components and set up event listeners.

            5. onActivityCreated(Bundle)

            Called when the Activity's onCreate() method has completed.
            The Fragment is fully initialized, and the Activity is fully created.

            6. onStart()

            Called when the Fragment becomes visible to the user.
            The Fragment is now interacting with the user.

            7. onResume()

            Called when the Fragment is resumed and becomes the topmost Fragment.
            The Fragment is now fully visible and interactive.

            8. onPause()

            Called when the Fragment is paused and no longer the topmost Fragment.
            The Fragment is no longer fully visible and interactive.

            9. onStop()

            Called when the Fragment is stopped and no longer visible to the user.
            The Fragment is no longer interacting with the user.

            10. onDestroyView()

            Called when the Fragment's view is destroyed.
            The Fragment's view is no longer valid, and you should release any resources.

            11. onDestroy()

            Called when the Fragment is destroyed.
            The Fragment is no longer valid, and you should release any resources.

            12. onDetach()

            Called when the Fragment is detached from the Activity.
            The Fragment is no longer attached to the Activity.
        """.trimIndent(),
        code = """
            public class MyFragment extends Fragment {
                private ListView listView;

                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    // Initialize variables and load data
                }

                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    View view = inflater.inflate(R.layout.my_fragment, container, false);
                    listView = view.findViewById(R.id.list_view);
                    return view;
                }

                @Override
                public void onViewCreated(View view, Bundle savedInstanceState) {
                    // Initialize UI components and set up event listeners
                    listView.setAdapter(new MyAdapter(getActivity(), myList));
                }

                @Override
                public void onStart() {
                    super.onStart();
                    // Fragment is now visible to the user
                }

                @Override
                public void onResume() {
                    super.onResume();
                    // Fragment is now fully visible and interactive
                }

                @Override
                public void onPause() {
                    super.onPause();
                    // Fragment is no longer fully visible and interactive
                }

                @Override
                public void onStop() {
                    super.onStop();
                    // Fragment is no longer visible to the user
                }

                @Override
                public void onDestroyView() {
                    super.onDestroyView();
                    // Release resources
                }

                @Override
                public void onDestroy() {
                    super.onDestroy();
                    // Release resources
                }

                @Override
                public void onDetach() {
                    super.onDetach();
                    // Fragment is no longer attached to the Activity
                }
            }
        """.trimIndent()
    )
}

@Preview
@Composable
fun FragmentLifecycleScreenPreview() {
    FragmentLifecycleScreen(navController = rememberNavController())
}