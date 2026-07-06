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

    /**
     * The [FakeApiService] scenario is controlled by the constructor default in FakeApiService.kt.
     * Edit `private val scenario: ApiScenario = ...` THERE to simulate a different API outcome
     * (success, network error, timeout, server error, invalid JSON). Mirrors the Flutter/iOS fakes.
     */
    @Provides
    @Singleton
    fun provideFakeApiService(): FakeApiService = FakeApiService()

    @Provides
    @Singleton
    fun provideApiService(fake: FakeApiService): ApiService = fake
}
