package com.jefferson.bestfdjtest.sportsleague.data.core

import com.jefferson.bestfdjtest.errors.domain.model.TechnicalError
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesLocalDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.model.LeagueEntity
import com.jefferson.bestfdjtest.sportsleague.data.core.worker.ILeaguesSyncWorkScheduler
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LeaguesRepository @Inject constructor(
    private val syncScheduler: ILeaguesSyncWorkScheduler,
    private val localDataSource: ILeaguesLocalDataSource,
    private val networkDataSource: ILeaguesNetworkDataSource,
) : ILeaguesRepository {

    @Throws(TechnicalError::class)
    override suspend fun requiresSync(): Boolean = localDataSource.isEmpty()

    @Throws(TechnicalError::class)
    override suspend fun scheduleSyncs(
        intervalDays: Long,
    ) {
        syncScheduler.scheduleWorkers(
            intervalDays = intervalDays,
            update = requiresSync(),
        )
    }

    @Throws(TechnicalError::class)
    override suspend fun sync(
    ) {
        try {
            val upToDateLeagues = networkDataSource.listAll()

            try {
                localDataSource.emplace(
                    leagues = upToDateLeagues,
                )
            } catch (e: Throwable) {
                throw TechnicalError.Database(cause = e)
            }
        } catch (e: Throwable) {
            if (e is CancellationException) throw e
            throw TechnicalError.Network(cause = e)
        }
    }

    override fun observePeriodicSyncs(
    ): Flow<Boolean> = syncScheduler.observeSyncRuns()
        .map { isAnyRunning ->
            isAnyRunning || requiresSync()
        }
        .catch { e ->
            if (e is CancellationException) throw e
            throw TechnicalError.Database(cause = e)
        }

    @Throws(TechnicalError::class)
    override suspend fun getLeagueById(
        leagueId: String,
    ): League = try {
        localDataSource.getLeagueById(
            leagueId,
        )
    } catch (e: Throwable) {
        if (e is CancellationException) throw e
        throw TechnicalError.Network(cause = e)
    }
        .toDomain()

    override fun listAll(
    ): Flow<List<League>> = localDataSource.listAll(
    )
        .map {
            it.map { league ->
                league.toDomain()
            }
        }
        .catch { e ->
            if (e is CancellationException) throw e
            throw TechnicalError.Database(cause = e)
        }

    override fun searchInNames(
        query: String,
    ): Flow<List<League>> = localDataSource.searchInNames(
        query = query
    )
        .map {
            it.map { league -> league.toDomain() }
        }
        .catch { e ->
            if (e is CancellationException) throw e
            throw TechnicalError.Database(cause = e)
        }

    @Throws(TechnicalError.Data::class)
    private fun LeagueEntity.toDomain(
    ): League = League(
        id = id ?: throw TechnicalError.Data("field \"id\" is missing"),
        sport = sport ?: throw TechnicalError.Data("field \"sport\" is missing"),
        name = name ?: throw TechnicalError.Data("field \"name\" is missing"),
        altName = altName.orEmpty(),
    )

    companion object {
        private const val TAG: String = "LeaguesRepository"
    }
}
