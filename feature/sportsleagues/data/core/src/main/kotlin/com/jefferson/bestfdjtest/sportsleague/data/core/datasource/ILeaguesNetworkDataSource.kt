package com.jefferson.bestfdjtest.sportsleague.data.core.datasource

import com.jefferson.bestfdjtest.sportsleague.data.core.model.LeagueEntity

interface ILeaguesNetworkDataSource {
    suspend fun listAll(
    ): List<LeagueEntity>

    suspend fun getLeagueDetails(
        leagueId: String,
    ): LeagueEntity
}
