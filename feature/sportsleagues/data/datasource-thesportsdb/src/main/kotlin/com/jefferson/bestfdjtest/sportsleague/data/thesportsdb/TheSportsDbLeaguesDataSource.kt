package com.jefferson.bestfdjtest.sportsleague.data.thesportsdb

import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ILeaguesNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.model.LeagueEntity
import com.jefferson.thesportsdb.client.TheSportsDbApiClient
import com.jefferson.thesportsdb.client.model.TheSportsDBV1LeagueDto
import javax.inject.Inject

class TheSportsDbLeaguesDataSource @Inject constructor(
    private val apiClient: TheSportsDbApiClient,
) : ILeaguesNetworkDataSource {

    override suspend fun listAll(): List<LeagueEntity> = apiClient.listAllLeagues(
    ).map {
        it.toEntity()
        LeagueEntity(
            id = it.idLeague,
            sport = it.strSport,
            name = it.strLeague,
            altName = it.strLeagueAlternate,
        )
    }

    override suspend fun getLeagueDetails(
        leagueId: String,
    ): LeagueEntity = apiClient.getLeague(
        leagueId = leagueId,
    )
        .toEntity()

    private fun TheSportsDBV1LeagueDto.toEntity(): LeagueEntity = LeagueEntity(
        id = idLeague,
        sport = strSport,
        name = strLeague,
        altName = strLeagueAlternate,
    )

    companion object {
        private const val TAG: String = "LeaguesNetworkDataSource"
    }
}
