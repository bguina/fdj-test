package com.jefferson.bestfdjtest.sportsleague.data.thesportsdb

import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ITeamsNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.model.TeamEntity
import com.jefferson.thesportsdb.client.TheSportsDbApiClient
import com.jefferson.thesportsdb.client.model.TheSportsDBV1TeamDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TheSportsDbTeamsDataSource @Inject constructor(
    private val apiClient: TheSportsDbApiClient,
) : ITeamsNetworkDataSource {

    override suspend fun searchTeams(
        query: String,
    ): List<TeamEntity> = withContext(Dispatchers.IO) {
        apiClient.searchTeams(
            query = query,
        ).map {
            it.toEntity()
        }
    }

    private fun TheSportsDBV1TeamDto.toEntity(): TeamEntity = TeamEntity(
        id = idLeague,
        name = strTeam,
        badgeImageUrl = strTeamBadge,
        leaguesNames = listOfNotNull(
            strLeague,
            strLeague2,
            strLeague3,
            strLeague4,
            strLeague5,
            strLeague6,
            strLeague7,
        )
    )

    companion object {
        private const val TAG: String = "TheSportsDbTeamsDataSource"
    }
}
