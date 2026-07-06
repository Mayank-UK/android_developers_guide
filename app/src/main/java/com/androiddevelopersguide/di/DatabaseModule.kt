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

/**
 * Shared database wiring for all flavors (dev / staging / prod). Each flavor uses the same real
 * Room database, so this lives in `main` rather than being duplicated per flavor.
 *
 * DB error-handling paths are verified via MockK unit tests, not in-app wiring — see
 * [com.androiddevelopersguide.data.local.daos.FailingPostDao] for a reference decorator you can
 * swap in temporarily for manual checks.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

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
