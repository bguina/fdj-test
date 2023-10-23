package com.jefferson.bestfdjtest.sportsleagues.domain.repository

import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team

interface ITeamsRepository {

    suspend fun searchTeamsFromLeague(
        leagueName: String,
    ): List<Team>

}
