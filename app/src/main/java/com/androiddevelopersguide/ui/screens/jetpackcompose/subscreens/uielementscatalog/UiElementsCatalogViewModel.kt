package com.androiddevelopersguide.ui.screens.jetpackcompose.subscreens.uielementscatalog

import androidx.lifecycle.ViewModel
import com.androiddevelopersguide.domain.models.LinkData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UiElementsCatalogViewModel @Inject constructor(
) : ViewModel() {

    // Catalog entries are surfaced here as the UI elements land. The placeholder "Temp"
    // entry (which navigated to dead scaffolding) was removed during the code-review cleanup.
    val links: List<LinkData> = emptyList()
}