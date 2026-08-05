package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.workmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.androiddevelopersguide.workmanagers.CounterRepository
import com.androiddevelopersguide.workmanagers.CounterWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.util.concurrent.TimeUnit
import javax.inject.Inject

// WorkManagerViewModel.kt
@HiltViewModel
class WorkManagerViewModel @Inject constructor(
    private val workManager: WorkManager,
    counterRepository: CounterRepository
) : ViewModel() {

    // The UI observes the persisted counter directly — the single source of truth. Each
    // periodic CounterWorker run reads → increments → writes this value, so it advances across
    // runs even though PeriodicWorkRequest does not chain output into the next run's input.
    val counter: StateFlow<Int> = counterRepository.counter
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    fun startCounter() {
        val workRequest = PeriodicWorkRequestBuilder<CounterWorker>(15, TimeUnit.MINUTES)
            .build()

        workManager.enqueueUniquePeriodicWork(
            COUNTER_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    fun stopCounter() {
        workManager.cancelUniqueWork(COUNTER_WORK_NAME)
    }

    companion object {
        private const val COUNTER_WORK_NAME = "counter_work"
    }
}
