package com.androiddevelopersguide.di

import android.content.Context
import androidx.room.Room
import com.androiddevelopersguide.data.local.AppDatabase
import com.androiddevelopersguide.data.local.daos.FailingPostDao
import com.androiddevelopersguide.data.local.daos.PostDao
import com.androiddevelopersguide.util.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlavorDatabaseModule {

    /**
     * The dev flavor uses the **real** Room database (same as prod/staging), so storage behaves
     * exactly like production and survives restarts. On top of it we layer [FailingPostDao], a
     * thin decorator that injects DB failures for exercising the error-handling paths.
     *
     * To simulate a different DB outcome, edit the `scenario` constructor default in
     * [FailingPostDao] (success / empty / read error / write error). Mirrors the iOS/Flutter
     * guide fakes.
     */
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePostDao(database: AppDatabase): PostDao {
        // `scenario` is intentionally not passed here so the constructor default in
        // FailingPostDao is the single place you edit to switch DB scenarios.
        return FailingPostDao(inner = database.postDao())
    }
}
