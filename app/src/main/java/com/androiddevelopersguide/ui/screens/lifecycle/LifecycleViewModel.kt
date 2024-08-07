package com.androiddevelopersguide.ui.screens.lifecycle

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LifecycleViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Activity lifecycle", routePath = NavRouter.ActivityLifecycleScreen.route),
        LinkData(title = "Broadcast receiver lifecycle", routePath = NavRouter.BroadcastReceiverScreen.route),
        LinkData(title = "Fragment lifecycle", routePath = NavRouter.FragmentLifecycleScreen.route),
        LinkData(title = "Jetpack compose lifecycle", routePath = NavRouter.JetpackComposeLifecycleReferScreen.route),
        LinkData(title = "Service lifecycle", routePath = NavRouter.ServiceLifecycleScreen.route),
        LinkData(title = "View model lifecycle", routePath = NavRouter.ViewModelLifecycleScreen.route),
    )

}