package com.androiddevelopersguide.ui.screens.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevelopersguide.domain.models.AppTheme
import com.androiddevelopersguide.domain.usecases.ObserveThemeUseCase
import com.androiddevelopersguide.domain.usecases.SetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * App-scoped holder of the user's theme preference. Exposes the current [AppTheme] and the
 * operations the chrome (app bar + drawer) needs: cycle through modes and set an explicit mode.
 */
@HiltViewModel
class ThemeViewModel @Inject constructor(
    observeTheme: ObserveThemeUseCase,
    private val setTheme: SetThemeUseCase
) : ViewModel() {

    val theme: StateFlow<AppTheme> = observeTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = AppTheme.System
        )

    fun setTheme(mode: AppTheme) {
        viewModelScope.launch { setTheme(mode) }
    }

    /**
     * Cycles Light -> Dark -> System -> Light. Used by the app-bar toggle.
     */
    fun toggle() {
        val next = when (theme.value) {
            AppTheme.Light -> AppTheme.Dark
            AppTheme.Dark -> AppTheme.System
            AppTheme.System -> AppTheme.Light
        }
        setTheme(next)
    }

    private companion object {
        const val STOP_TIMEOUT_MILLIS = 5_000L
    }
}
