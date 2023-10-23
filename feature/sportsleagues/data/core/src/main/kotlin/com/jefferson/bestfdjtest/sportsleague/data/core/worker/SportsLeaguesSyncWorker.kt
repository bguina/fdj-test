package com.jefferson.bestfdjtest.sportsleague.data.core.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.jefferson.bestfdjtest.errors.domain.repository.IExceptionRepository
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber
import java.io.IOException

/**
 * Sync leagues table from API.
 * */
@HiltWorker
class SportsLeaguesSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val leaguesRepository: ILeaguesRepository,
    private val exceptionRepository: IExceptionRepository,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = try {
        leaguesRepository.sync()
        Result.success()
    } catch (e: IOException) {
        exceptionRepository.reportException(e)
        Timber.tag(TAG).e(e)
        Result.retry()
    } catch (e: Throwable) {
        exceptionRepository.reportException(e)
        Timber.tag(TAG).e(e)
        Result.failure()
    }

    companion object {
        const val TAG: String = "SportsLeaguesSyncWorker"
    }
}
