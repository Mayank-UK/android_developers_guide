package com.androiddevelopersguide.domain.repositoryinterfaces

import com.androiddevelopersguide.domain.models.AppTheme
import kotlinx.coroutines.flow.Flow

/**
 * Persists the user's theme preference. Default value is [AppTheme.System].
 */
interface ThemeRepository {
    fun observeTheme(): Flow<AppTheme>
    suspend fun setTheme(theme: AppTheme)
}
