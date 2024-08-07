package com.androiddevelopersguide.ui.screens.deployment

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeploymentViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Test deployment", routePath = NavRouter.TestDeploymentScreen.route),
        LinkData(title = "Prod deployment", routePath = NavRouter.ProdDeploymentScreen.route),
    )
}