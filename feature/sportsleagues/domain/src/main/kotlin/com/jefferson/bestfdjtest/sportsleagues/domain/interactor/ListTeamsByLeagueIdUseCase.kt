package com.jefferson.bestfdjtest.sportsleagues.domain.interactor

import com.jefferson.bestfdjtest.sportsleagues.domain.model.Team
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ILeaguesRepository
import com.jefferson.bestfdjtest.sportsleagues.domain.repository.ITeamsRepository
import javax.inject.Inject

class ListTeamsByLeagueIdUseCase @Inject constructor(
    private val leaguesRepository: ILeaguesRepository,
    private val teamsRepository: ITeamsRepository,
) {

    suspend operator fun invoke(
        leagueId: String,
    ): List<Team> {
        val league = leaguesRepository.getLeagueById(leagueId = leagueId)
        val teams = teamsRepository.searchTeamsFromLeague(leagueName = league.name)

        return teams
            .sortedByDescending {
                // business rule: "ordre anti-alphabétique"
                it.name
            }
            .filterIndexed { index, _ ->
                // business rule: "affichant qu’1 équipe sur 2"
                // whether we should exclude evens or odds is not specified
                0 == (index % 2)
            }
    }
}
