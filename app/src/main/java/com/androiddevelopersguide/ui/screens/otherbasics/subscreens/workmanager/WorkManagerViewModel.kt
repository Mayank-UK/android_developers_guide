package com.androiddevelopersguide.ui.screens.otherbasics.subscreens.workmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.androiddevelopersguide.workmanagers.CounterRepository
import com.androiddevelopersguide.workmanagers.CounterWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

// WorkManagerViewModel.kt
@HiltViewModel
class WorkManagerViewModel @Inject constructor(
    private val workManager: WorkManager
) : ViewModel() {

    val counter: StateFlow<Int> = CounterRepository.counter

    fun startCounter() {
        val initialCount = counter.value
        val inputData = workDataOf(CounterWorker.KEY_COUNTER to initialCount)

        val workRequest = PeriodicWorkRequestBuilder<CounterWorker>(15, TimeUnit.MINUTES)
            .setInputData(inputData)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "counter_work",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    fun stopCounter() {
        workManager.cancelUniqueWork("counter_work")
    }

    init {
        workManager.getWorkInfosForUniqueWorkLiveData("counter_work")
            .asFlow()
            .onEach { workInfoList ->
                workInfoList.lastOrNull()?.let { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val newCount = workInfo.outputData.getInt(CounterWorker.KEY_COUNTER, counter.value)
                        CounterRepository.setCounter(newCount)
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}