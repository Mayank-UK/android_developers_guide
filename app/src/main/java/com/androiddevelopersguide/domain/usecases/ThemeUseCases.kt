package com.androiddevelopersguide.domain.usecases

import com.androiddevelopersguide.domain.models.AppTheme
import com.androiddevelopersguide.domain.repositoryinterfaces.ThemeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveThemeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {
    operator fun invoke(): Flow<AppTheme> = repository.observeTheme()
}

class SetThemeUseCase @Inject constructor(
    private val repository: ThemeRepository
) {
    suspend operator fun invoke(theme: AppTheme) = repository.setTheme(theme)
}
