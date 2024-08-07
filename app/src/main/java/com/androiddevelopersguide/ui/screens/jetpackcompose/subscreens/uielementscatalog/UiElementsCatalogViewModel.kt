package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uielementscatalog

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import com.androiddevelopersguide.ui.navigation.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UiElementsCatalogViewModel @Inject constructor(
) : ViewModel() {

    val links = listOf(
        LinkData(title = "Temp", routePath = NavRouter.TempScreen.route),
    )
}