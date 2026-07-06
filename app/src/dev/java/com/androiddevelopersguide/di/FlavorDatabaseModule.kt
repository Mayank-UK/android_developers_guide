package com.androiddevelopersguide.di

import android.content.Context
import androidx.room.Room
import com.androiddevelopersguide.data.local.AppDatabase
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
     * exactly like production and survives restarts. The DAO is provided plainly, with no
     * failure-injection wrapper — DB error paths are verified via MockK unit tests, not in the
     * app wiring. See [com.androiddevelopersguide.data.local.daos.FailingPostDao] for a reference
     * decorator you can swap in temporarily for manual checks.
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
    fun providePostDao(database: AppDatabase): PostDao = database.postDao()
}
