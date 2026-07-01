package com.androiddevelopersguide.di

import com.androiddevelopersguide.data.remote.ApiService
import com.androiddevelopersguide.data.remote.FakeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlavorNetworkModule {
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return FakeApiService()
    }
}
