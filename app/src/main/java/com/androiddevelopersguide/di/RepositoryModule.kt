package com.androiddevelopersguide.di

import com.androiddevelopersguide.data.repositoryimplementations.PostRepositoryImpl
import com.androiddevelopersguide.data.repositoryimplementations.ThemeRepositoryImpl
import com.androiddevelopersguide.domain.repositoryinterfaces.PostRepository
import com.androiddevelopersguide.domain.repositoryinterfaces.ThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Binds repository interfaces to their implementations. Both impls already carry
 * `@Inject constructor`, so `@Binds` is the idiomatic, lower-overhead way to wire them
 * (Hilt generates the factory rather than us hand-writing a `@Provides`). Scoping lives
 * here as the single source of truth — the impls themselves are not annotated with `@Singleton`.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostRepository(impl: PostRepositoryImpl): PostRepository

    @Binds
    @Singleton
    abstract fun bindThemeRepository(impl: ThemeRepositoryImpl): ThemeRepository
}
