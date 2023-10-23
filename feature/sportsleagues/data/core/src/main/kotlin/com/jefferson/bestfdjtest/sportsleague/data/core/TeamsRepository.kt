package com.jefferson.bestfdjtest.sportsleague.data.core

import com.jefferson.bestfdjtest.errors.domain.model.TechnicalError
import com.jefferson.bestfdjtest.sportsleague.data.core.datasource.ITeamsNetworkDataSource
import com.jefferson.bestfdjtest.sportsleague.data.core.model.TeamEntity
import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ITeamsRepository
import kotlinx.coroutines.CancellationException
import timber.log.Timber
import javax.inject.Inject

class TeamsRepository @Inject constructor(
    private val networkDataSource: ITeamsNetworkDataSource,
) : ITeamsRepository {

    @Throws(TechnicalError::class)
    override suspend fun searchTeamsFromLeague(
        leagueName: String,
    ): List<Team> {
        val teams = try {
            networkDataSource.searchTeams(
                query = leagueName,
            )
        } catch (e: Throwable) {
            if (e is CancellationException) throw e
            throw TechnicalError.Network(cause = e)
        }

        return teams
            .filter { team ->
                // searching all fields for a league name may yield false positives
                true == team.leaguesNames?.any { it.equals(leagueName, true) }
            }
            .map { team -> team.toDomain() }
    }

    @Throws(TechnicalError.Data::class)
    private fun TeamEntity.toDomain(
    ): Team = Team(
        id = id ?: throw TechnicalError.Data("field \"id\" is missing"),
        name = name.orEmpty(),
        badgeImageUrl = badgeImageUrl,
        leaguesNames = leaguesNames.orEmpty(),
    )

    companion object {
        private const val TAG: String = "TeamsRepository"
    }
}
