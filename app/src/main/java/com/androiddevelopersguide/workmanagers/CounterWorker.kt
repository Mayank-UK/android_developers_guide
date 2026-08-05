package com.androiddevelopersguide.workmanagers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Dedicated DataStore file for the periodic counter. Lives at the file's top level so both
 * [CounterWorker] (via its [android.app.Application] context) and [CounterRepository]
 * (via Hilt-injected context) read/write the exact same persisted state.
 */
private val Context.counterDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "counter_preferences"
)

private val COUNTER_KEY = intPreferencesKey("counter")

/**
 * Increments a counter on every run.
 *
 * IMPORTANT: [androidx.work.PeriodicWorkRequest] does NOT chain one run's output data into the
 * next run's input — so seeding the work with `setInputData(...)` only affects the FIRST run.
 * To make the counter actually advance across periodic runs, we persist it here (read → increment
 * → write to DataStore) and return the new value as output for any observer. The UI observes the
 * persisted flow directly via [CounterRepository].
 */
class CounterWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        var newCount = 0
        applicationContext.counterDataStore.edit { prefs ->
            val current = prefs[COUNTER_KEY] ?: 0
            newCount = current + 1
            prefs[COUNTER_KEY] = newCount
        }
        return Result.success(workDataOf(KEY_COUNTER to newCount))
    }

    companion object {
        const val KEY_COUNTER = "key_counter"
    }
}

/**
 * Single source of truth for the counter value. Backed by the same DataStore [CounterWorker]
 * writes to, so the UI reflects increments performed by periodic work even after process death.
 */
@Singleton
class CounterRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val counter: Flow<Int> = context.counterDataStore.data.map { prefs ->
        prefs[COUNTER_KEY] ?: 0
    }
}
