package com.androiddevelopersguide.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Provides the single app-scoped DataStore for preferences-backed state (e.g. theme). */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.appPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "app_preferences"
    )

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.appPreferencesDataStore
}
