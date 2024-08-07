package com.androiddevelopersguide.ui.screens.lifecycle.subscreens.viewmodellifecycle

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelLifecycleViewModel @Inject constructor(
) : ViewModel() {

}