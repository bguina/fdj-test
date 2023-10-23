package com.jefferson.bestfdjtest.sportsleague.data.core.worker

import android.app.Application
import androidx.lifecycle.asFlow
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SportsLeaguesSyncWorkScheduler @Inject constructor(
    application: Application,
) : ILeaguesSyncWorkScheduler {

    private val workManager: WorkManager = WorkManager.getInstance(application)

    override suspend fun scheduleWorkers(intervalDays: Long, update: Boolean) {
        workManager.enqueueUniquePeriodicWork(
            SportsLeaguesSyncWorker.TAG,
            if (update) ExistingPeriodicWorkPolicy.UPDATE
            else ExistingPeriodicWorkPolicy.KEEP,
            PeriodicWorkRequestBuilder<SportsLeaguesSyncWorker>(
                intervalDays,
                TimeUnit.DAYS
            )
                .build()
        )
    }

    override fun observeSyncRuns(
    ): Flow<Boolean> = workManager
        .getWorkInfosForUniqueWorkLiveData(SportsLeaguesSyncWorker.TAG)
        .asFlow()
        .map { workInfoList ->
            val isAnyRunning = workInfoList.any { it.state == WorkInfo.State.RUNNING }
            isAnyRunning
        }
}
