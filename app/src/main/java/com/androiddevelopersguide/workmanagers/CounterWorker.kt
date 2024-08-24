package com.androiddevelopersguide.workmanagers
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CounterWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val currentCount = inputData.getInt(KEY_COUNTER, 0)
        val newCount = currentCount + 1
        CounterRepository.setCounter(newCount)

        // Create the output data with the new count
        val outputData = workDataOf(KEY_COUNTER to newCount)

        return Result.success(outputData)
    }

    companion object {
        const val KEY_COUNTER = "key_counter"
    }
}

object CounterRepository {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    fun setCounter(newCount: Int) {
        _counter.value = newCount
    }
}