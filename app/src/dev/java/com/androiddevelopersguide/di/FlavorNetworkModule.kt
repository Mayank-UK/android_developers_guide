package com.androiddevelopersguide.di

import com.androiddevelopersguide.BuildConfig
import com.androiddevelopersguide.data.remote.ApiService
import com.androiddevelopersguide.data.remote.FailureInjectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlavorNetworkModule {

    /**
     * The dev flavor uses the **real** Retrofit client hitting [BuildConfig.BASE_URL] (same as
     * prod/staging), with a [FailureInjectionInterceptor] layered in front of it to simulate API
     * failures on the client side.
     *
     * To simulate a different API outcome, edit the `scenario` constructor default in
     * [FailureInjectionInterceptor] (success / network error / timeout / server error / invalid
     * JSON). It is provided as a singleton so it can also be toggled at runtime from a debug menu.
     */
    @Provides
    @Singleton
    fun provideFailureInjectionInterceptor(): FailureInjectionInterceptor =
        FailureInjectionInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        failureInterceptor: FailureInjectionInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(failureInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
