package com.androiddevelopersguide.di

import com.androiddevelopersguide.data.local.daos.PostDao
import com.androiddevelopersguide.data.remote.ApiService
import com.androiddevelopersguide.data.repositoryimplementations.PostRepositoryImpl
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePostRepository(
        api: ApiService,
        dao: PostDao
    ): PostRepository {
        return PostRepositoryImpl(api, dao)
    }
}