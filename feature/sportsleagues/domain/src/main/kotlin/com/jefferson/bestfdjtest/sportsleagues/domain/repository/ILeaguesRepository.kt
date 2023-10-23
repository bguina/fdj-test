package com.jefferson.bestfdjtest.sportsleagues.domain.repository

import com.jefferson.bestfdjtest.errors.domain.model.TechnicalError
import com.jefferson.bestfdjtest.sportsleagues.domain.model.League
import kotlinx.coroutines.flow.Flow

interface ILeaguesRepository {

    /**
     * Returns true when leagues table is empty and a [sync] run is required.
     * */
    @Throws(TechnicalError::class)
    suspend fun requiresSync(): Boolean

    /**
     * Schedule workers to periodically run [sync] work.
     *  @param intervalDays interval at which leagues cache must be updated.
     * */
    @Throws(TechnicalError::class)
    suspend fun scheduleSyncs(
        intervalDays: Long,
    )

    /**
     * Update leagues cache from source of truth.
     * */
    @Throws(TechnicalError::class)
    suspend fun sync(
    )

    /**
     * Keep track of the repository [sync] work status.
     * Emits true when a sync is occurring
     * Emits false when a sync is successful and is no longer running.
     * May emit an error of type [TechnicalError].
     * */
    fun observePeriodicSyncs(
    ): Flow<Boolean>

    @Throws(TechnicalError::class)
    suspend fun getLeagueById(
        leagueId: String,
    ): League

    /**
     * May emit an error of type [TechnicalError].
     * */
    fun listAll(
    ): Flow<List<League>>

    /**
     * May emit an error of type [TechnicalError].
     * */
    fun searchInNames(
        query: String,
    ): Flow<List<League>>

}
