package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import com.jefferson.bestfdjtest.errors.domain.interactor.ReportExceptionUseCase
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ScheduleAndObserveWorkersUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepository,
    private val reportExceptionUseCase: ReportExceptionUseCase,
) {

    operator fun invoke(
    ): Flow<Boolean> = leaguesRepository.observePeriodicSyncs()
        .onStart { leaguesRepository.scheduleSyncs(LEAGUES_REFRESH_INTERVAL_DAYS) }
        .catch { e ->
            reportExceptionUseCase(e)
            throw e
        }

    companion object {
        private const val LEAGUES_REFRESH_INTERVAL_DAYS: Long = 7
    }
}
