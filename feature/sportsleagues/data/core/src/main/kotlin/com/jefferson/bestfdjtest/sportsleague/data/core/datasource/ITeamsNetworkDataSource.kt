package com.jefferson.bestfdjtest.sportsleague.data.core.datasource

import com.jefferson.bestfdjtest.sportsleague.data.core.model.TeamEntity

interface ITeamsNetworkDataSource {

    suspend fun searchTeams(
        query: String,
    ): List<TeamEntity>

}
