package com.androiddevelopersguide.ui.screens.otherbasics

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherBasicsViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Activity", routePath = NavRouter.ActivityScreen.route),
        LinkData(title = "Intent", routePath = NavRouter.IntentScreen.route),
        LinkData(title = "Service", routePath = NavRouter.ServiceScreen.route),
        LinkData(title = "Broadcast receiver", routePath = NavRouter.BroadcastReceiverScreen.route),
        LinkData(title = "Content provider", routePath = NavRouter.ContentProviderScreen.route),
    )
}