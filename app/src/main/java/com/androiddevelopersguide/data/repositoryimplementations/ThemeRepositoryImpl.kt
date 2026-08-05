package com.androiddevelopersguide.data.repositoryimplementations

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.androiddevelopersguide.domain.models.AppTheme
import com.androiddevelopersguide.domain.repositoryinterfaces.ThemeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore Preferences-backed implementation of [ThemeRepository]. The preference key mirrors
 * the React reference app (`rdg-theme`). Missing values resolve to [AppTheme.System].
 * Scoping is declared once at the DI binding ([com.androiddevelopersguide.di.RepositoryModule]).
 */
class ThemeRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {

    private val key = stringPreferencesKey(AppTheme.STORAGE_KEY)

    override fun observeTheme(): Flow<AppTheme> =
        dataStore.data.map { preferences -> AppTheme.safeFromString(preferences[key]) }

    override suspend fun setTheme(theme: AppTheme) {
        dataStore.edit { it[key] = theme.name }
    }
}
