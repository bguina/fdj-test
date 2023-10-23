package com.jefferson.bestfdjtest.sportsleague.data.core.worker

import kotlinx.coroutines.flow.Flow

interface ILeaguesSyncWorkScheduler {
    suspend fun scheduleWorkers(
        intervalDays: Long,
        update: Boolean
    )

    fun observeSyncRuns(
    ): Flow<Boolean>
}
