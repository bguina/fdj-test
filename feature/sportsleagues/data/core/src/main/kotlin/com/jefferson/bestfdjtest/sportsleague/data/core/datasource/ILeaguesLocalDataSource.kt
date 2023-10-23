package com.jefferson.bestfdjtest.sportsleague.data.core.datasource

import com.jefferson.bestfdjtest.sportsleague.data.core.model.LeagueEntity
import kotlinx.coroutines.flow.Flow

interface ILeaguesLocalDataSource {

    suspend fun emplace(
        leagues: List<LeagueEntity>,
    )

    suspend fun isEmpty(): Boolean

    suspend fun getLeagueById(
        leagueId: String,
    ): LeagueEntity

    fun listAll(
    ): Flow<List<LeagueEntity>>

    fun searchInNames(
        query: String,
    ): Flow<List<LeagueEntity>>

}
