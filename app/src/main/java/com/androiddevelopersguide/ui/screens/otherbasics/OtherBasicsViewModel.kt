package com.androiddevelopersguide.ui.screens.otherbasics

import android.os.Build
import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherBasicsViewModel @Inject constructor(
) : ViewModel() {

    val links: List<LinkData> = buildList {
        add(LinkData(title = "Activity", routePath = NavRouter.ActivityScreen.route))
        add(LinkData(title = "Intent", routePath = NavRouter.IntentScreen.route))
        add(LinkData(title = "Service", routePath = NavRouter.ServiceScreen.route))
        add(LinkData(title = "Broadcast receiver", routePath = NavRouter.BroadcastReceiverScreen.route))
        add(LinkData(title = "Work manager", routePath = NavRouter.WorkManagerScreen.route))
        // The Notification destination only renders content on Android 13+ (TIRAMISU); hide the
        // entry on older APIs so users never navigate to a blank screen.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            add(LinkData(title = "Notification", routePath = NavRouter.NotificationScreen.route))
        }
        add(LinkData(title = "Content provider", routePath = NavRouter.ContentProviderScreen.route))
    }
}
